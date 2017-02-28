package services;

import models.daos.ClientDAO;
import models.daos.DriverDAO;
import models.pojos.Driver;

/**
 * Created by Yerlan on 26.02.2017.
 */
public class ClientService {

    public static boolean updateOrder(Long clientid, Long orderid) {
        if (ClientDAO.updateOrder(clientid,orderid)) return true;
        return false;
    }
}
