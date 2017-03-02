package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Car;
import org.springframework.stereotype.Service;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface ICarService {
    Car getCar(Long id);
}
