package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Service;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IDriverService {
    Driver getDriver(Long id);
}
