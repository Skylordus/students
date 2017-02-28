package services;

import models.daos.AdminDAO;
import models.daos.ClientDAO;
import models.daos.DriverDAO;
import models.daos.UserDAO;
import models.pojos.Admin;
import models.pojos.Client;
import models.pojos.Driver;
import models.pojos.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;

/**
 * Created by Yerlan on 26.02.2017.
 */
public class UserService {
    static Logger logger = Logger.getLogger(UserService.class);
    public static void register(HttpServletRequest request) {
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
            ClientDAO.insert(client);
        } else if (role.equals("driver")) {
            Driver driver = (Driver) user;
            driver.setCar((long)0);
            driver.setExperience_years((long)0);
            DriverDAO.insert(driver);
        } else if (role.equals("admin")) {
            Admin admin = (Admin) user;
            AdminDAO.insert(admin);
        }
    }

    public static User authorize(String login, String password) {

        User user = UserDAO.getByLoginAndPassword(login, password);

        return user;
    }
}
