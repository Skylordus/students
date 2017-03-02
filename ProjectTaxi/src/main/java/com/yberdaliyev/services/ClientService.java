package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class ClientService implements IClientService {
    private IClientDAO clientDAO;

    @Autowired
    public ClientService(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public boolean updateOrder(Long clientid, Long orderid) {
        if (clientDAO.updateOrder(clientid,orderid)) return true;
        return false;
    }
}
