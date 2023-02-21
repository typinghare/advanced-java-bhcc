package us.james.microapp.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Metadata {
    String key();

    String type() default RegistryType.STRING;

    String description() default "";

    class RegistryType {
        public static final String CLASS = "Class";         // Class<?>
        public static final String ENUM = "Enum";           // Class<? extends Enum>
        public static final String STRING = "String";       // String
        public static final String NUMBER = "Number";       // Long
        public static final String BOOLEAN = "Boolean";     // Boolean
    }
}
