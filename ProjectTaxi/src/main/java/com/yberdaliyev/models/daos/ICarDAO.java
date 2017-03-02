package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Repository;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface ICarDAO {
    boolean insert(Driver driver);

    Car getById(long id);
}
