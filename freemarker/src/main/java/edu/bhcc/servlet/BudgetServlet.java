package edu.bhcc.servlet;

import edu.bhcc.TransactionService;
import edu.bhcc.model.Transaction;
import edu.bhcc.utils.FreeMarkers;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Budget Servlet based on FreeMarker.
 */
public class BudgetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        List<Transaction> transactionList = null;
        try {
            transactionList = new TransactionService().getAllTransaction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (transactionList == null) {
            response.getWriter().println("Fail to load transactions!");
        }

        // FreeMarker data.
        final Map<String, Object> data = new HashMap<>();
        data.put("transactionList", transactionList);

        // Get FreeMarker template.
        final Template template = FreeMarkers.getTemplate("budget");
        final StringWriter stringWriter = new StringWriter();

        try {
            template.process(data, stringWriter);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().print(stringWriter);
    }
}
