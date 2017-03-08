package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class OrderService implements IOrderService {
    private IOrderDAO orderDAO;

    @Autowired
    public OrderService(IOrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public Order getOrder(Long id) {
       Order order = orderDAO.getById(id);
        return order;
    }

    @Override
    public ArrayList<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public Order generateOrder(String id, String from, String to, String price, String client, String driver, String status, String time) {
        Order order = new Order();
        order.setId(Long.valueOf(id);
        order.setFrom(from);
        order.setTo(request.getParameter("to"));
        order.setClient(Long.valueOf(request.getParameter("client")));
        order.setDriver(Long.valueOf(request.getParameter("driver")));
        order.setPrice_per_km(Long.valueOf(request.getParameter("price")));
        order.setStatus(Long.valueOf(request.getParameter("status")));
        order.setPickup_time(Time.valueOf(request.getParameter("pickup_time")));
        return null;
    }

    public Long insert(Order order) {
         return orderDAO.insert(order);
    }

    public boolean delete(Long id) {
        if (orderDAO.deleteById(id)) {
            return true;
        }
        return false;
    }
}
