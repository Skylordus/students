package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Order;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class OrderDAO implements IOrderDAO {
    private static Logger logger = Logger.getLogger(OrderDAO.class);

    private static final String SQL_INSERT_ORDER = "INSERT INTO main.orders " +
                                                   "(\"from\", \"to\", price_per_km, client, status, pickup_time) "+
                                                   "values (?,?,?,?,?,?);";
    private static final String SQL_SELECT_ID = "SELECT * FROM main.orders " +
                                                "WHERE id=?;";
    private static final String SQL_SELECT_MAXID = "SELECT max(id) FROM main.orders;";
    private static final String SQL_DELETE = "DELETE FROM main.orders WHERE id=?;";
    private static final String SQL_GET_ALL_ORDERS = "SELECT * FROM main.orders ORDER BY id ASC";

    @Override
    public Long insert(Order order) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_ORDER)) {
            prepS.setString(1,order.getFrom());
            prepS.setString(2,order.getTo());
            prepS.setLong(3,order.getPrice_per_km());
            prepS.setLong(4,order.getClient());
            prepS.setLong(5,order.getStatus());
            prepS.setTime(6,order.getPickup_time());
            if (prepS.executeUpdate()>0) {
                ResultSet rs = conn.prepareStatement(SQL_SELECT_MAXID).executeQuery();
                rs.next();
                Long id = rs.getLong("max");
                return id;
            }
        } catch (SQLException e) {
            logger.error("sql error in orderDAO.insert() method",e);
        }
        return null;
    }

    @Override
    public Order getById(long id) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
            prepS.setLong(1,id);
            ResultSet resultSet = prepS.executeQuery();
            Order order = new Order();
            if (!resultSet.next()) return null;
            order.setId(id);
            order.setClient(resultSet.getLong("client"));
            order.setDriver(resultSet.getLong("driver"));
            order.setFrom(resultSet.getString("from"));
            order.setTo(resultSet.getString("to"));
            order.setPrice_per_km(resultSet.getLong("price_per_km"));
            order.setStatus(resultSet.getLong("status"));
            return order;
        } catch (SQLException e ) {
            logger.error("error",e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_DELETE)) {
            prepS.setLong(1,id);
            if (prepS.executeUpdate()>0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("sql error in orderDAO.insert() method",e);
        }
        return false;
    }

    @Override
    public ArrayList<Order> getAll() {
        Order order;
        ArrayList<Order> orders = new ArrayList<>();
        try (Connection conn = Connector.getConnection();
            PreparedStatement prepS = conn.prepareStatement(SQL_GET_ALL_ORDERS) ) {
            ResultSet resultSet = prepS.executeQuery();
            while (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setFrom(resultSet.getString("from"));
                order.setTo(resultSet.getString("to"));
                order.setPrice_per_km(resultSet.getLong("price_per_km"));
                order.setClient(resultSet.getLong("client"));
                order.setDriver(resultSet.getLong("driver"));
                order.setStatus(resultSet.getLong("status"));
                order.setPickup_time(resultSet.getTime("pickup_time"));
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("sql error in get all orders",e);
        }
        return orders;
    }

}
