package com.yberdaliyev;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

/**
 * Created by Yerlan on 06.03.2017.
 */
public class HomeControllerTest {
    @Test
    public void handleRequest() throws Exception {
        HomeController controller = new HomeController();
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("/hello.jsp", modelAndView.getViewName());
    }

}