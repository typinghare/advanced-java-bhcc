# Database Homework

## SQL Statements

1. To create a database called "advanced_java_bhcc":

   ~~~sql
   CREATE DATABASE `advanced_java_bhcc`;
   ~~~

2. To create a table called "weather" in "advanced_java_bhcc" database:

   ~~~sql
   USE `advanced_java_bhcc`;
   CREATE TABLE `weather` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `date` VARCHAR(16) NOT NULL,
     `precipitation` FLOAT NOT NULL,
     `tempMax` FLOAT NOT NULL,
     `tempMin` FLOAT NOT NULL,
     `wind` FLOAT NOT NULL,
     `weather` VARCHAR(16) NOT NULL,
     PRIMARY KEY (`id`)
   );
   ~~~

3. To insert a record into the database (example):

   ~~~sql
   INSERT INTO `weather` 
   (`date`, `precipitation`, `tempMax`, `tempMin`, `win`, `weather`) 
   VALUES 
   ('2012-01-01', 0, 12.8 ,5, 4.7, 'drizzle');
   ~~~

4. To find the first record that satisfies given conditions (example):

   ~~~sql
   SELECT * FROM 'weather' WHERE `date` = '2014-03-12';
   ~~~

## Tests

### Configure the Database

Set up the configuration in `edu.bhcc.database.constant.DatabaseConstant` before testing.

~~~java
public class DatabaseConstant {
    public static final String URL = "localhost";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Input your password here.";
    public static final String DATABASE_NAME = "Input your database name here.";
}
~~~

### Load Data from CSV File

Run the following command:

~~~bash
mvn compile exec:java -Dexec.mainClass="edu.bhcc.database.DataLoader"
~~~

Then, input the path of the CSV file, it should be: `src/main/java/resources/seattle-weather.csv`. Then the program will
start reading the file, store the data, and insert records into the database.

### Run Tests

1. Run the following command. The program will soon block for listening to sockets.

   ~~~bash
   mvn compile exec:java -Dexec.mainClass="edu.bhcc.database.WeatherServer"
   ~~~

2. Open another terminal window and run the following maven test command:

   ~~~bash
   mvn test
   ~~~

## About the Implementation
> I am sorry that I may have the Encapsulation Paranoid. In the past I just imported third-party libraries, 
> like ORM libraries such as Hibernate and MyBatis, to finish my work. 
> Now I would like to try if I can code up a simple one by myself.