package com.yberdaliyev.controllers.servlets;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.services.IOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Yerlan on 27.02.2017.
 */
@Controller
public class AdminServlet {
    private static Logger logger = Logger.getLogger(AdminServlet.class);
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/admin_account", method = RequestMethod.POST)
    public ModelAndView doPost(@RequestParam(name = "type") String type,
                               @RequestParam(name = "id") String id,
                               @RequestParam(name = "from") String from,
                               @RequestParam(name = "to") String to,
                               @RequestParam(name = "client") String client,
                               @RequestParam(name = "driver") String driver,
                               @RequestParam(name = "price") String price,
                               @RequestParam(name = "status") String status,
                               @RequestParam(name = "pickup_time") String pickup_time) {

        logger.trace("on doPOST AdminServlet");
        ModelAndView modelAndView = new ModelAndView("admin_account");

        if ((type!=null)&&(type.equals("edit"))) {
            Order order = orderService.generateOrder(id,
                                                    from,
                                                    to,
                                                    price,
                                                    client,
                                                    driver,
                                                    status,
                                                    pickup_time);
            orderService.insert(order);}
        ArrayList<Order> orders = orderService.getAll();
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }

    @RequestMapping(value = "/admin_account", method = RequestMethod.GET)
    public ModelAndView doGet() {
        logger.trace("on doGet AdminServlet");
        ArrayList<Order> orders = orderService.getAll();
        ModelAndView modelAndView = new ModelAndView("admin_account");

        modelAndView.addObject("orders",orders);
        return modelAndView;
    }
}
