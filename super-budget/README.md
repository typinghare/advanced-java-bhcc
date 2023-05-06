# Super Budget

# Basic Information

* **Project name**: Super Budget
* **Group member**: Zhuojian Chen (James) (Individual Project)
* **Environment**:
    * Java: `19.0.2`
    * Maven: `3.9.1`
    * MySQL: `8.0.32`

> <span style="font-size: 1.2em; font-weight: bold;">Statement</span> \
> This project is intended to be developed by the author without utilizing AI-based tools such as Copilot and ChatGPT.
> All references for this project are listed in the end of this document.

> <span style="font-size: 1.2em; font-weight: bold;">Intention</span> \
> Develop a robust, highly available, and scalable web-based budgeting application that adheres to industry best
> practices. The project structure should be designed to optimize performance and maintainability, while avoiding the
> inclusion of overkilling and overly complex features. 😋

> <span style="font-size: 1.2em; font-weight: bold;">Javadoc and Comments</span> \
> For this project, elaborated Javadoc and comments will be added to classes, fields, methods, and code blocks,
> except the following cases:
> * Ordinary getters and setters in classes annotated by `@JavaBean` or `@Entity`.
> * Fields in model classes annotated by `@Entity`. Because the comment of fields in model classes are written inside
    the `@Comment` annotation.
> * Override methods.
> * Constructors annotated by `@Autowired` in Spring bean classes.
> * Test methods in test classes.

# Project Structure

# Application Properties

This project has only one `.properties` file, which is the default `application.properties`. The following is the
explanation for each property.

~~~properties
# The log asks to disable it to avoid warning.
spring.jpa.open-in-view=false
# Disable the annoying banner.
spring.main.banner-mode=off
# Prints SQL statements on the terminal.
spring.jpa.show-sql=true
# To facilitate testing, the item is set to "create",
# which instructs Hibernate to drop all tables and create new ones each time the application or test is executed.
spring.jpa.hibernate.ddl-auto=create
# The url of the database.
spring.datasource.url=jdbc:mysql://localhost:3306/budget
# The username.
spring.datasource.username=root
# The password.
spring.datasource.password=******
~~~

# Testing

Testing environment should include:

* Java `19.0.x`
* Maven `3.8.x`
* A running MySQL `8.0.32` service.

Prior to testing, it is necessary for the testers to update the database URL, username, and password fields in the
the `application.properties` file.

# References

1. [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
2. [Java - Documentation Comments](https://www.tutorialspoint.com/java/java_documentation.htm)
3. [16 Best Practices for Spring Boot in Production](https://www.springcloud.io/post/2022-08/springboot-best-practices)
4. [Advanced Spring Data JPA - Specifications and Querydsl](https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl)
5. [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
6. [How to truncate a foreign key constrained table?](https://stackoverflow.com/questions/5452760/how-to-truncate-a-foreign-key-constrained-table)
7. [How to get domain name from URL in JavaScript](https://www.javatpoint.com/how-to-get-domain-name-from-url-in-javascript#:~:text=The%20most%20straightforward%20way%20to,location.)