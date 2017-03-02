package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Admin;
import org.springframework.stereotype.Repository;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IAdminDAO {
    boolean insert(Admin admin);
}
