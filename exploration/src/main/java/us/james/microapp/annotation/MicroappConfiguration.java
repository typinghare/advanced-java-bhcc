package us.james.microapp.annotation;

import org.springframework.stereotype.Component;


@Component
public @interface MicroappConfiguration {
    /**
     * Returns the id of this microapp.
     * @return the id of this microapp.
     */
    String appId();

    /**
     * Returns the ids of dependencies of this microapp.
     * @return the ids of dependencies of this microapp.
     */
    String[] dependencies();

    /**
     * Returns the array of medata of this microapp.
     * @return the array of medata of this microapp.
     */
    Metadata[] metadata();
}
