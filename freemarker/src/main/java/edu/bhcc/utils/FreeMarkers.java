package edu.bhcc.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

/**
 * FreeMarker utility class. I have to use static things because I don't have Spring here.
 */
public class FreeMarkers {
    /**
     * The extension of template files.
     */
    private static final String TEMPLATE_FILE_EXTENSION = ".ftl";

    /**
     * FreeMaker configuration.
     */
    private static final Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

    static {
        // Copy-pasting professor's code...
        try {
            configuration.setDirectoryForTemplateLoading(new File("src/main/templates"));

            configuration.setDefaultEncoding("UTF-8");

            // Sets how errors will appear.
            // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // Don't log exceptions inside FreeMarker that it will throw at you anyway:
            configuration.setLogTemplateExceptions(false);

            // To accommodate to how JDBC returns values; see Javadoc!
            configuration.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a FreeMarker template.
     * @param templateName the name of the template
     * @return a FreeMarker template.
     */
    public static Template getTemplate(String templateName) throws IOException {
        return configuration.getTemplate(templateName + TEMPLATE_FILE_EXTENSION);
    }
}
