package com.yberdaliyev.services;

import org.springframework.stereotype.Service;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientService {
    boolean updateOrder(Long clientid, Long orderid);
}
