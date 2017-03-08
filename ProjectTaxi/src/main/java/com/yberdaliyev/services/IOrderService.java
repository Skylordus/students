package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Yerlan on 02.03.2017.
 */
public interface IOrderService {
    Order generateOrder(Long id, String from, String to, Long price, Long client, Long driver, Long status, String time);
    Long insert(Order order);
    boolean delete(Long id);
    Order getOrder(Long id);
    ArrayList<Order> getAll();

}
