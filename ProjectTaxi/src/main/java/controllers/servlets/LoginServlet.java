package controllers.servlets;

import models.pojos.Admin;
import models.pojos.Client;
import models.pojos.Driver;
import models.pojos.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yerlan on 25.02.2017.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("userlogin");
        String password = request.getParameter("userpassword");
        HttpSession session = request.getSession();
        User user = UserService.authorize(login,password);
        session.setAttribute("user_object",user);
        session.setMaxInactiveInterval(10*60);

        if (user instanceof Admin) {
            session.setAttribute("user_role","admin");
            request.getRequestDispatcher("/admin_account").forward(request,response);
        } else
        if (user instanceof Driver) {
            session.setAttribute("user_role","driver");
            request.getRequestDispatcher("/driver_account").forward(request, response);
        } else
        if (user instanceof Client) {
            session.setAttribute("user_role","client");
            request.getRequestDispatcher("/user_account").forward(request, response);
        } else {
            request.setAttribute("failed","visible");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
