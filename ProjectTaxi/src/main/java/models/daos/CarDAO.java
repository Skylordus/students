package models.daos;

import models.connectors.Connector;
import models.pojos.Car;
import models.pojos.Driver;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CarDAO {
    private static Logger logger = Logger.getLogger(CarDAO.class);

    private static final String SQL_INSERT_DRIVER = "INSERT INTO main.drivers " +
            "(firstname,lastname,patronymic,birthdate,login,email) "+
            "values (?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM main.cars " +
            "WHERE id=?";


    public static boolean insert(Driver driver) {
        try (PreparedStatement prepS = UserDAO.insert(SQL_INSERT_DRIVER, driver) ) {
            if (prepS.executeUpdate() > 0) return true;
        } catch (SQLException e) {
            logger.error("sql error in insert driver method",e);
        }
        return false;
    }

    public static Car getById(long id) {
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
