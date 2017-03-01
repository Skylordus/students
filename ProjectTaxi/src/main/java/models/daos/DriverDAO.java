package models.daos;

import models.connectors.Connector;
import models.pojos.Driver;
import models.pojos.User;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DriverDAO {
    private static Logger logger = Logger.getLogger(DriverDAO.class);

    private static final String SQL_INSERT_DRIVER = "INSERT INTO main.drivers " +
            "(firstname,lastname,patronymic,birthdate,login,email) "+
            "values (?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM main.drivers " +
            "WHERE id=?";


    public static boolean insert(Driver driver) {
        try (PreparedStatement prepS = UserDAO.insert(SQL_INSERT_DRIVER, driver) ) {
            if (prepS.executeUpdate() > 0) return true;
        } catch (PSQLException e) {
            logger.trace(e.getCause());
            logger.error("PSQL exc. in insert driver method",e);
        } catch (SQLException e) {
            logger.error("SQL exc. in insert driver method",e);
        }
        return false;
    }

    public static Driver getById(long id) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
            prepS.setLong(1,id);
            ResultSet resultSet = prepS.executeQuery();
            Driver driver = new Driver();
            resultSet.next();
            UserDAO.setFields(driver,resultSet);
            driver.setExperience_years(resultSet.getLong("experience_years"));
            driver.setCar(resultSet.getLong("car"));
            return driver;
        } catch (SQLException e ) {
            logger.error("error",e);
        }
        return null;
    }
}
