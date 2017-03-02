package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IDriverDAO;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class DriverService implements IDriverService {
    private IDriverDAO driverDAO;

    @Autowired
    public DriverService(IDriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public Driver getDriver(Long id) {

       Driver driver = driverDAO.getById(id);

        return driver;
    }
}
