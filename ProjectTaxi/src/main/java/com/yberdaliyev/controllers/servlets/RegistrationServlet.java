package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.services.IUserService;
import org.apache.log4j.Logger;
import com.yberdaliyev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Controller
public class RegistrationServlet extends HttpServlet {
    @Autowired
    private IUserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("on POST Registration servlet");
        logger.trace("Bean userService = "+userService);
        userService.register(request);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("on GET Registration servlet");
        request.getRequestDispatcher("/registration.jsp").forward(request,response);
    }


}
