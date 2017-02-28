package models.daos;

import models.connectors.Connector;
import models.pojos.Client;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientDAO {
    private static Logger logger = Logger.getLogger(ClientDAO.class);

    private static final String SQL_INSERT_CLIENT = "INSERT INTO main.clients " +
            "(firstname,lastname,patronymic,birthdate,login,email, date_registered) "+
            "values (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE main.clients " +
            "SET \"order\"=? WHERE id=?;";

    public static boolean insert(Client client) {
        try (PreparedStatement prepS = UserDAO.insert(SQL_INSERT_CLIENT, client) ){
            prepS.setDate(10,client.getDate_registered());
            if (prepS.executeUpdate() > 0) return true;
        } catch (SQLException e) {
            logger.error("sql error in insert client method",e);
        }
        return false;
    }

    public static boolean updateOrder(Long clientid, Long orderid) {
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
