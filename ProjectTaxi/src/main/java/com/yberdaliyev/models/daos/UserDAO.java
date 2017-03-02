package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Admin;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAO implements IUserDAO {
    private static Logger logger = Logger.getLogger(UserDAO.class);
    private static final String SQL_INSERT_LOGIN = "INSERT INTO main.logins " +
            "(login,pwd,type) "+
            "values (?,?,?);";
    private static final String SQL_SELECT_LOGIN = "SELECT * FROM main.logins " +
                                                   "WHERE login=? AND pwd=?";

    @Override
    public PreparedStatement insert(String statement, User user) {
        try {Connection conn = Connector.getConnection();
            PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_LOGIN+statement);
            prepS.setString(1,user.getLogin());
            prepS.setString(2,user.getPwd());

            int type=1;
            if (user instanceof Client) {type=2;} else
            if (user instanceof Driver) {type=3;}
            prepS.setInt(3,type);

            prepS.setString(4,user.getFirstname());
            prepS.setString(5,user.getLastname());
            prepS.setString(6,user.getPatronymic());
            prepS.setDate(7,user.getBirthdate());
            prepS.setString(8,user.getLogin());
            prepS.setString(9,user.getEmail());
            return prepS;
        } catch (SQLException e) {
            logger.error("sql error in insertUser method",e);
        }
        return null;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        try (Connection conn = Connector.getConnection();
            PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_LOGIN)) {
            prepS.setString(1,login);
            prepS.setString(2,password);
            ResultSet rs = prepS.executeQuery();
            if ( !rs.next() ) return null;
            String query = "SELECT * FROM ";
            int type = rs.getInt("type");
            switch (type) {
                case 1:
                    query += "main.admins ";
                    break;
                case 2:
                    query += "main.clients ";
                    break;
                case 3:
                    query += "main.drivers ";
            }
            query += "WHERE login=?";
            PreparedStatement prep = conn.prepareStatement(query);
            prep.setString(1,login);
            rs = prep.executeQuery();
            rs.next();
            switch (type) {
                case 1:
                    Admin admin = new Admin();
                    setFields(admin,rs);
                    return admin;
                case 2:
                    Client client = new Client();
                    setFields(client,rs);
                    client.setDate_registered(rs.getDate("date_registered"));
                    client.setOrders_amount(rs.getLong("orders_amount"));
                    client.setOrder(rs.getLong("order"));
                    return client;
                case 3:
                    Driver driver = new Driver();
                    setFields(driver,rs);
                    driver.setCar(rs.getLong("car"));
                    driver.setExperience_years(rs.getLong("experience_years"));
                    return driver;
            }
        } catch (SQLException e) {
            logger.error("SQL exception: getBY login and password UserDAO",e);
        }
        return null;
    }

    @Override
    public void setFields(User user, ResultSet rs) throws SQLException {
        user.setBirthdate(rs.getDate("birthdate"));
        user.setLastname(rs.getString("lastname"));
        user.setFirstname(rs.getString("firstname"));
        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPatronymic(rs.getString("patronymic"));
        user.setId(rs.getLong("id"));
    }

}
