package controllers.servlets;

import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

/**
 * Created by Yerlan on 26.02.2017.
 */
@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("on POST Registration servlet");
        UserService.register(request);
        response.sendRedirect("/login");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("on GET Registration servlet");
        request.getRequestDispatcher("/registration.jsp").forward(request,response);
    }


}
