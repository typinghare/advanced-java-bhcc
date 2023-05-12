package edu.bhcc.superbudget.controller;

import edu.bhcc.superbudget.dto.BudgetDto;
import edu.bhcc.superbudget.dto.TransactionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Index controller, including some methods for static resources retrieving.
 */
@Controller
public class IndexController implements ErrorController {
    /**
     * Index controller logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * Do nothing.
     * @return template name.
     */
    @GetMapping("/")
    public String index(Model model) {
        // This is only for hinting the IDE.
        model.addAttribute("budgetDtoList", List.of(new BudgetDto()));
        model.addAttribute("transactionDtoList", List.of(new TransactionDto()));

        return "index";
    }

    /**
     * Retrieves static resources.
     * @param request http request
     * @return response entity.
     * @throws IOException if an error is encountered when input.
     */
    @GetMapping("/static/**")
    public ResponseEntity<String> staticResources(HttpServletRequest request) throws IOException {
        final String filepath = request.getRequestURI();
        final File file = new File("src/main/resources/" + filepath);
        final String absolutePath = file.getAbsolutePath();


        if (!file.exists()) {
            logger.warn("File not exist: " + filepath);
            throw new FileNotFoundException("File not exist: " + filepath);
        }

        final String extension = com.google.common.io.Files.getFileExtension(file.getName());
        final String contentType = switch (extension) {
            case "css" -> "text/css;charset=utf-8";
            case "js" -> "text/js;charset=utf-8";
            case "woff2" -> "font/woff2";
            case "ttf" -> "font/ttf";
            default -> "";
        };

        final String fileContent = new String(Files.readAllBytes(Path.of(absolutePath)));
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", contentType);

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }

    /**
     * When expected errors are encountered, the request will be dispatched to this controller.
     * @param model the model
     * @return template name.
     */
    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "Unexpected exception was caught. Please check your input.");

        return "index";
    }
}
