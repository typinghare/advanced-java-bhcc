package edu.bhcc.superbudget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration file enables developers to define properties and register them with Spring beans.
 */
@Configuration
@EnableWebMvc
public class SuperBudgetConfiguration implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public SuperBudgetConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Interceptors registration.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final SuperBudgetInterceptor superBudgetInterceptor = applicationContext.getBean(SuperBudgetInterceptor.class);

        registry.addInterceptor(superBudgetInterceptor);
    }
}
