package org.example.project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("data", LocalDateTime.now());
        req.setAttribute(
                "users",
                Arrays.asList(
                        new User("Alex", "Voronin", 37),
                        new User("Garry", "Dubo", 37),
                        new User("Dimon", "Gavrilov", 37),
                        new User("Maxouny", "Zhuk", 37)));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
