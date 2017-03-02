package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Repository;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IDriverDAO {
    boolean insert(Driver driver);

    Driver getById(long id);
}
