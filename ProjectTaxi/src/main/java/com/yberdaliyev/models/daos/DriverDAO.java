package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Driver;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DriverDAO implements IDriverDAO {
    private IUserDAO userDAO;

    @Autowired
    public DriverDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private static Logger logger = Logger.getLogger(DriverDAO.class);

    private static final String SQL_INSERT_DRIVER = "INSERT INTO main.drivers " +
            "(firstname,lastname,patronymic,birthdate,login,email) "+
            "values (?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM main.drivers " +
            "WHERE id=?";


    @Override
    public boolean insert(Driver driver) {
        try (PreparedStatement prepS = userDAO.insert(SQL_INSERT_DRIVER, driver) ) {
            if (prepS.executeUpdate() > 0) return true;
        } catch (PSQLException e) {
            logger.trace(e.getCause());
            logger.error("PSQL exc. in insert driver method",e);
        } catch (SQLException e) {
            logger.error("SQL exc. in insert driver method",e);
        }
        return false;
    }

    @Override
    public Driver getById(long id) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
            prepS.setLong(1,id);
            ResultSet resultSet = prepS.executeQuery();
            Driver driver = new Driver();
            resultSet.next();
            userDAO.setFields(driver,resultSet);
            driver.setExperience_years(resultSet.getLong("experience_years"));
            driver.setCar(resultSet.getLong("car"));
            return driver;
        } catch (SQLException e ) {
            logger.error("error",e);
        }
        return null;
    }
}
