package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Client;
import org.springframework.stereotype.Repository;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientDAO {
    boolean insert(Client client);

    boolean updateOrder(Long clientid, Long orderid);
}
