package landing_13;

import freemarker.template.Template;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import landing_13.utils.FreeMarkers;

import java.io.IOException;

public class LandingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Template template = FreeMarkers.getTemplate("landing");

        resp.getWriter().println(template.toString());
    }
}
