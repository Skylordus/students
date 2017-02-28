package services;

import models.daos.DriverDAO;
import models.daos.OrderDAO;
import models.pojos.Driver;
import models.pojos.Order;

/**
 * Created by Yerlan on 26.02.2017.
 */
public class DriverService {

    public static Driver getDriver(Long id) {

       Driver driver = DriverDAO.getById(id);

        return driver;
    }
}
