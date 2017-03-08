package com.yberdaliyev.controllers.servlets;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.services.IOrderService;
import org.apache.log4j.Logger;
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
import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Yerlan on 27.02.2017.
 */
@Controller
public class AdminServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AdminServlet.class);
    @Autowired
    private IOrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("on doPOST AdminServlet");
        String type = request.getParameter("type");
        String from = request.getParameter("from");
        String to = request.getParameter("to");

        if ((type!=null)&&(type.equals("edit"))) {
            Order order = new Order();
            order.setId(Long.valueOf(request.getParameter("id")));
            order.setFrom(request.getParameter("from"));
            order.setTo(request.getParameter("to"));
            order.setClient(Long.valueOf(request.getParameter("client")));
            order.setDriver(Long.valueOf(request.getParameter("driver")));
            order.setPrice_per_km(Long.valueOf(request.getParameter("price")));
            order.setStatus(Long.valueOf(request.getParameter("status")));
            order.setPickup_time(Time.valueOf(request.getParameter("pickup_time")));
        }
        ArrayList<Order> orders = orderService.getAll();
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/admin_account.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("on doGet AdminServlet");
        ArrayList<Order> orders = orderService.getAll();
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/admin_account.jsp").forward(request,response);
    }
}
