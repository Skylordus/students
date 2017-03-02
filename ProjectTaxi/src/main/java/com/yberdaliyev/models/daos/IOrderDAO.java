package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Order;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IOrderDAO {
    boolean deleteById(Long id);

    Long insert(Order order);

    Order getById(long id);
}
