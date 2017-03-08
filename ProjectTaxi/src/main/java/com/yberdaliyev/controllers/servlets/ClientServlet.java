package com.yberdaliyev.controllers.servlets;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import org.apache.log4j.Logger;
import com.yberdaliyev.services.*;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;

/**
 * Created by Yerlan on 27.02.2017.
 */
@Controller
public class ClientServlet {
    private static Logger logger = Logger.getLogger(ClientServlet.class);
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IDriverService driverService;
    @Autowired
    private ICarService carService;

    @RequestMapping(value = "/user_account", method = RequestMethod.POST)
    public ModelAndView doPost(HttpSession session,
                               @RequestParam(name = "type") String type,
                               @RequestParam(name = "from") String from,
                               @RequestParam(name = "to") String to,
                               @RequestParam(name = "pickup_time") String pickup_time,
                               @RequestParam(name = "plan") String plan) {
        ModelAndView modelAndView = new ModelAndView("user_account");
        Client client = (Client) session.getAttribute("user_object");
        logger.warn("in do Post type = "+type);

        if (type == null) {} else
        if (type.equals("new_order")){
            Order order = generateOrder(client,
                                        from,
                                        to,
                                        pickup_time,
                                        plan);
            Long id = orderService.insert(order);
            logger.error("in new order = "+order.toString());
            client.setOrder(id);
            clientService.updateOrder(client.getId(),id);

        } else if (type.equals("cancel_order")) {
            logger.trace(client.getId());
            clientService.updateOrder(client.getId(),(long)0);
            orderService.delete(client.getOrder());
            client.setOrder((long)0);
        }
        session.setAttribute("user_object",client);
        return generateUserPage(modelAndView, client);
    }

    @RequestMapping(value = "/user_account", method = RequestMethod.GET)
    public ModelAndView doGet(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("user_account");
        Client client = (Client) session.getAttribute("user_object");
        return generateUserPage(modelAndView,client);
    }

    private ModelAndView generateUserPage(ModelAndView modelAndView, Client client) {
        modelAndView.addObject("login",client.getFirstname());
        Long orderId = client.getOrder();
        if ( (orderId == 0)||(orderId == null) ) {
            modelAndView.addObject("ordered",null);
        } else {
            modelAndView.addObject("ordered","true");
            Order order = orderService.getOrder(client.getOrder());
            modelAndView.addObject("from",order.getFrom());
            modelAndView.addObject("to",order.getTo());
            long i = order.getPrice_per_km();
            String str = ""+i+" Rubles/km";
            if (i==16) {
                str+=" (Economy)";
            } else
            if (i==25) {
                str+=" (Comfort)";
            } else {
                str+=" (Business)";
            }
            modelAndView.addObject("price", str);
            modelAndView.addObject("id",order.getId());
            i=order.getDriver();
            String str2;
            if (i>0) {
                Driver driver = driverService.getDriver(i);
                str=driver.toString();
                Car car = carService.getCar(driver.getCar());
                str2=car.toString();
            } else {
                str="not assigned yet";
                str2="not assigned yet";
            }
            modelAndView.addObject("driver",str);
            modelAndView.addObject("car",str2);
            i=order.getStatus();
            if (i==0) {
                modelAndView.addObject("status","waiting for a driver");
            } else if (i==1) {
                modelAndView.addObject("status","driver assigned");
            } else if (i==2) {
                modelAndView.addObject("status","order fulfilled");
            }
        }
        return modelAndView;
    }

    private Order generateOrder(Client client,
                                String from,
                                String to,
                                String pickup_time,
                                String plan) {

        Order order = new Order();
        order.setFrom(from);
        order.setTo(to);
        order.setPickup_time(Time.valueOf(pickup_time+":00"));
        order.setStatus((long)0);
        order.setClient(client.getId());
        String str = plan;
        if ( str.equals("economy") ) {
            order.setPrice_per_km((long) 16);
        } else if ( str.equals("comfort") ) {
            order.setPrice_per_km((long) 25);
        } else order.setPrice_per_km((long) 40);
        return order;
    }
}
