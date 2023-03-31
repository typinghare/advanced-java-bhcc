package edu.bhcc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A rudimentary database class that can perform basic functions. (I tried to implement a basic ORM so the
 * code looks verbose)
 */
public class Database implements AutoCloseable {
    /**
     * Database logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(Database.class);

    /**
     * Database config.
     */
    private final Config config;

    /**
     * Connection instance.
     */
    private final Connection connection;

    /**
     * Creates a Database.
     * @param config database config
     */
    public Database(Config config) throws SQLException {
        this.config = config;

        final String url = config.url + config.database;
        connection = DriverManager.getConnection(url, config.username(), config.password());
    }

    /**
     * Inserts a model to the database. The name of the model class is regarded as the name of the table.
     * @param model model class (should be a JavaBean)
     * @return the primary key (id); -1 if fail
     */
    public long insert(Object model) {
        final Class<?> modelClass = model.getClass();
        final String tableName = toCamelCase(modelClass.getSimpleName());
        final Map<String, Object> map = new HashMap<>();

        // use reflection to access all getters
        for (final Method method : modelClass.getDeclaredMethods()) {
            final String methodName = method.getName();
            if (methodName.startsWith("get") && methodName.length() > 3) {
                final String attributeName = Character.toString(methodName.charAt(3) ^ 32)
                    + methodName.substring(4);
                try {
                    map.put(attributeName, method.invoke(model));
                } catch (IllegalAccessException | InvocationTargetException ignore) {
                }
            }
        }

        if (map.size() < 1) return -1;

        // extract fields and values
        final List<String> fieldList = new ArrayList<>();
        final List<String> valueList = new ArrayList<>();
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() == null) continue;
            fieldList.add("`" + entry.getKey() + "`");
            valueList.add("'" + entry.getValue() + "'");
        }

        try {
            final String sql = "INSERT INTO `" + tableName + "` ("
                + String.join(",", fieldList) + ") values ("
                + String.join(",", valueList) + ");";
            if (config.toLogSql()) logger.info("[SQL] " + sql);

            final PreparedStatement preparedStatement
                = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Finds all satisfied records and returns a list of models. The name of the model class is regarded as
     * the name of the table.
     * @param modelClass  a model class
     * @param whereClause where clause
     * @param limit       limit
     * @return a list of model instances; null if errors occur
     */
    public <T> List<T> findAll(Class<T> modelClass, String whereClause, int limit) {
        final String tableName = toCamelCase(modelClass.getSimpleName());

        try {
            final String sql = "SELECT * FROM `" + tableName + "` WHERE " + whereClause + " LIMIT " + limit + ";";
            if (config.toLogSql()) logger.info("[SQL] " + sql);

            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();

            // collect model attributes
            final Map<String, Method> attributeMethodMap = new HashMap<>();
            for (final Method method : modelClass.getDeclaredMethods()) {
                final String methodName = method.getName();
                if (methodName.startsWith("set") && methodName.length() > 3) {
                    final String attributeName = toCamelCase(methodName.substring(3));
                    attributeMethodMap.put(attributeName, method);
                }
            }

            final List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                final T object = modelClass.getConstructor().newInstance();
                for (final String name : attributeMethodMap.keySet()) {
                    final Method method = attributeMethodMap.get(name);
                    final Class<?>[] types = method.getParameterTypes();
                    if (types.length == 0) continue;

                    final Class<?> type = types[0];
                    Object value = null;
                    if (type == Long.class) {
                        value = resultSet.getLong(name);
                    } else if (type == Double.class) {
                        value = resultSet.getDouble(name);
                    } else if (type == String.class) {
                        value = resultSet.getString(name);
                    } // ... should have more branches to cover all types

                    method.invoke(object, value);
                }
                list.add(object);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds the first satisfied records and returns a list of models. The name of the model class is regarded
     * as the name of the table.
     * @param modelClass  a model class
     * @param whereClause where clause
     * @return a model instance; null if no satisfied record or errors occur
     */
    public <T> T find(Class<T> modelClass, String whereClause) {
        List<T> list = findAll(modelClass, whereClause, 1);

        return list != null && list.size() >= 1 ? list.get(0) : null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a string from pascal case to camel case.
     * @param pascalCaseString the string to convert
     * @return the camel case of the given string
     */
    private String toCamelCase(String pascalCaseString) {
        return Character.toString(pascalCaseString.charAt(0) ^ 32) + pascalCaseString.substring(1);
    }

    /**
     * Database config.
     * @param url      the url of the database connection
     * @param username the username
     * @param password the password
     * @param database the database
     * @param toLogSql whether to log sql
     */
    public record Config(
        String url,
        String username,
        String password,
        String database,
        boolean toLogSql
    ) {
    }
}
