package services;

import models.daos.*;
import models.pojos.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by Yerlan on 26.02.2017.
 */
public class OrderService {
    public static Order getOrder(Long id) {
       Order order = OrderDAO.getById(id);
        return order;
    }

    public static Long insert(Order order) {
         return OrderDAO.insert(order);
    }

    public static boolean delete(Long id) {
        if (OrderDAO.deleteById(id)) {
            return true;
        }
        return false;
    }
}
