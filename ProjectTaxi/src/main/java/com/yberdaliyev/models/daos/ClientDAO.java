package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ClientDAO implements IClientDAO {
    private IUserDAO userDAO;

    @Autowired
    public ClientDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private static Logger logger = Logger.getLogger(ClientDAO.class);

    private static final String SQL_INSERT_CLIENT = "INSERT INTO main.clients " +
            "(firstname,lastname,patronymic,birthdate,login,email, date_registered) "+
            "values (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE main.clients " +
            "SET \"order\"=? WHERE id=?;";

    @Override
    public boolean insert(Client client) {
        try (PreparedStatement prepS = userDAO.insert(SQL_INSERT_CLIENT, client) ){
            prepS.setDate(10,client.getDate_registered());
            if (prepS.executeUpdate() > 0) return true;
        } catch (SQLException e) {
            logger.error("sql error in insert client method",e);
        }
        return false;
    }

    @Override
    public boolean updateOrder(Long clientid, Long orderid) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_UPDATE_ORDER) ){
            prepS.setLong(1, orderid);
            prepS.setLong(2, clientid);
            if (prepS.executeUpdate() > 0) return true;
        } catch (SQLException e) {
            logger.error("sql error in update client method",e);
        }
        return false;
    }
}
