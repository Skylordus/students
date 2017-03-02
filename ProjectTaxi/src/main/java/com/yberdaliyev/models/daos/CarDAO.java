package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CarDAO implements ICarDAO {
    private IUserDAO userDAO;

    @Autowired
    public CarDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private static Logger logger = Logger.getLogger(CarDAO.class);

    private static final String SQL_INSERT_DRIVER = "INSERT INTO main.drivers " +
            "(firstname,lastname,patronymic,birthdate,login,email) "+
            "values (?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM main.cars " +
            "WHERE id=?";


    @Override
    public boolean insert(Driver driver) {
        try (PreparedStatement prepS = userDAO.insert(SQL_INSERT_DRIVER, driver) ) {
            if (prepS.executeUpdate() > 0) return true;
        } catch (SQLException e) {
            logger.error("sql error in insert driver method",e);
        }
        return false;
    }

    @Override
    public Car getById(long id) {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_ID)) {
            prepS.setLong(1,id);
            ResultSet resultSet = prepS.executeQuery();
            Car car = new Car();
            resultSet.next();

            car.setColor(resultSet.getString("color"));
            car.setId(resultSet.getLong("id"));
            car.setManufacturer(resultSet.getString("manufacturer"));
            car.setModel(resultSet.getString("model"));
            car.setRegnum(resultSet.getString("regnum"));
            return car;
        } catch (SQLException e ) {
            logger.error("error",e);
        }
        return null;
    }
}
