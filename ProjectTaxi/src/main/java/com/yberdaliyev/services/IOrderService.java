package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by Yerlan on 02.03.2017.
 */
public interface IOrderService {
    Long generateOrder(String from, String to, Integer time);
    Long insert(Order order);
    boolean delete(Long id);
    Order getOrder(Long id);

}
