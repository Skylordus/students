package models.daos;

import models.connectors.Connector;
import models.pojos.Admin;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AdminDAO {
    private static Logger logger = Logger.getLogger(AdminDAO.class);

    private static final String SQL_INSERT_ADMIN = "INSERT INTO main.admins " +
                                                   "(firstname,lastname,patronymic,birthdate,login,email) "+
                                                   "values (?,?,?,?,?,?)";


    public static boolean insert(Admin admin) {
        try (PreparedStatement prepS = UserDAO.insert(SQL_INSERT_ADMIN, admin) ) {
             if (prepS.executeUpdate() > 0) return true;
        } catch (SQLException e) {
            logger.error("sql error in insert admin method",e);
        }
        return false;
    }

}
