package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.User;
import com.yberdaliyev.services.IUserService;
import com.yberdaliyev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Yerlan on 25.02.2017.
 */
@Controller
public class LoginServlet extends HttpServlet {
    @Autowired
    private IUserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("userlogin");
        String password = request.getParameter("userpassword");
        HttpSession session = request.getSession();
        User user = userService.authorize(login,password);
        session.setAttribute("user_object",user);
        session.setMaxInactiveInterval(10*60);
        session.setAttribute("incorrect_login",null);

        if (user instanceof Admin) {
            session.setAttribute("user_role",3);
            request.getRequestDispatcher("/admin_account").forward(request,response);
        } else
        if (user instanceof Driver) {
            session.setAttribute("user_role",2);
            request.getRequestDispatcher("/driver_account").forward(request, response);
        } else
        if (user instanceof Client) {
            session.setAttribute("user_role",1);
            request.getRequestDispatcher("/user_account").forward(request, response);
        } else {
            session.setAttribute("incorrect_login",1);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
