package org.example.project.servlet;

import org.example.project.service.JsonService;
import org.example.project.util.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class IndexServlet extends HttpServlet {

    private JsonService jsonService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("json", jsonService.getJson());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        Settings settings = new Settings();
        String fileName =
                Objects.requireNonNull(getClass().getClassLoader().getResource(settings.getValue("fileName"))).getPath();
        this.jsonService = new JsonService(fileName);
    }
}
