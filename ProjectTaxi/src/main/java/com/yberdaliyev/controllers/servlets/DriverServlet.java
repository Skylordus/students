package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yerlan on 27.02.2017.
 */
@Controller
public class DriverServlet {

    @RequestMapping(value = "/driver_account", method = RequestMethod.POST)
    public ModelAndView doPost( )  {
        ModelAndView modelAndView = new ModelAndView("driver_account");
        return modelAndView;
    }

    @RequestMapping(value = "/driver_account", method = RequestMethod.GET)
    public ModelAndView doGet( )  {
        ModelAndView modelAndView = new ModelAndView("driver_account");
        return modelAndView;
    }

}
