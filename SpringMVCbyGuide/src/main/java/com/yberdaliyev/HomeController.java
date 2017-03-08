package com.yberdaliyev;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yerlan on 06.03.2017.
 */
@Controller
public class HomeController {
    private static Logger logger = Logger.getLogger(HomeController.class);

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.trace("in HomeController::handleRequest");
        return new ModelAndView("/hello.jsp");
    }


}
