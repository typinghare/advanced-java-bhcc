package edu.bhcc.superbudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * The startup class for this project.
 * @author Zhuojian Chen (James)
 * @version 1.0.0
 * @see <a href="https://docs.google.com/document/d/1RS205KuOvmqX8IK5XV1zJWgVFxcXocIe5eKU2_pG5uo">Document</a>
 * @see <a href="https://www.loom.com/share/4c810a3a070f451b9a421fd5776b67f7">Video</a>
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {
    // Scan entities so that Hibernate can drop and create tables during initialization.
    edu.bhcc.superbudget.model.Category.class,
    edu.bhcc.superbudget.model.Transaction.class
})
public class SuperBudgetApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperBudgetApplication.class, args);
    }
}
