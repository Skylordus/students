package controllers.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yerlan on 25.02.2017.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("userlogin");
        String password = request.getParameter("userpassword");
        request.setAttribute("login",login);
        request.setAttribute("ordered","yes");
        request.setAttribute("id",123);
        request.setAttribute("from","Innopolis");
        request.setAttribute("to","Kazan");
        request.setAttribute("status","pending");
        request.getRequestDispatcher("/user_account.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
