package services;

import models.daos.CarDAO;
import models.daos.OrderDAO;
import models.pojos.Car;
import models.pojos.Order;

import java.lang.reflect.Proxy;

/**
 * Created by Yerlan on 26.02.2017.
 */
public class CarService {

    public static Car getCar(Long id) {

       Car car = CarDAO.getById(id);

        return car;
    }
}
