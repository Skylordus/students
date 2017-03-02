package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class UserService implements IUserService {

    static Logger logger = Logger.getLogger(UserService.class);

    private final IUserDAO IUserDAO;
    private final IAdminDAO IAdminDAO;
    private final IClientDAO IClientDAO;
    private final IDriverDAO IDriverDAO;

    @Autowired
    public UserService(IUserDAO IUserDAO, IAdminDAO IAdminDAO, IClientDAO IClientDAO, IDriverDAO IDriverDAO) {
        this.IUserDAO = IUserDAO;
        this.IAdminDAO = IAdminDAO;
        this.IClientDAO = IClientDAO;
        this.IDriverDAO = IDriverDAO;
    }

    public void register(HttpServletRequest request) {
        String role = request.getParameter("user_role");
        User user = null;

        if (role.equals("client")) {
            user = new Client();
        } else if (role.equals("driver")) {
            user = new Driver();
        } else if (role.equals("admin")) {
            user = new Admin();
        }

        user.setFirstname(request.getParameter("user_name"));
        user.setLastname(request.getParameter("user_surname"));
        user.setPatronymic(request.getParameter("user_patronymic"));
        String str = request.getParameter("user_birthdate");
        logger.trace(str);
        if (!str.isEmpty()) user.setBirthdate(Date.valueOf(str));
        user.setLogin(request.getParameter("user_login"));
        user.setPwd(request.getParameter("user_password"));
        user.setEmail(request.getParameter("user_email"));

        if (role.equals("client")) {
            Client client = (Client) user;
            client.setDate_registered(new Date(new java.util.Date().getTime()));
            client.setOrders_amount((long)0);
            IClientDAO.insert(client);
        } else if (role.equals("driver")) {
            Driver driver = (Driver) user;
            driver.setCar((long)0);
            driver.setExperience_years((long)0);
            IDriverDAO.insert(driver);
        } else if (role.equals("admin")) {
            Admin admin = (Admin) user;
            IAdminDAO.insert(admin);
        }
    }

    public User authorize(String login, String password) {

        User user = IUserDAO.getByLoginAndPassword(login, password);

        return user;
    }
}
