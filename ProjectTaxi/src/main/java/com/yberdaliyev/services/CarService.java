package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.CarDAO;
import com.yberdaliyev.models.daos.ICarDAO;
import com.yberdaliyev.models.pojos.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class CarService implements ICarService {

    private ICarDAO carDAO;

    @Autowired
    public CarService(ICarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car getCar(Long id) {
       Car car = carDAO.getById(id);
        return car;
    }
}
