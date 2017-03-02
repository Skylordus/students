package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Long generateOrder(String from, String to, Integer time){
        return 0l;
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
