package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Admin;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class AdminDAO implements IAdminDAO {
    private static Logger logger = Logger.getLogger(AdminDAO.class);

    private IUserDAO userDAO;

    private static final String SQL_INSERT_ADMIN = "INSERT INTO main.admins " +
                                                   "(firstname,lastname,patronymic,birthdate,login,email) "+
                                                   "values (?,?,?,?,?,?)";

    public AdminDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean insert(Admin admin) {
        try (PreparedStatement prepS = userDAO.insert(SQL_INSERT_ADMIN, admin) ) {
             if (prepS.executeUpdate() > 0) return true;
        } catch (SQLException e) {
            logger.error("sql error in insert admin method",e);
        }
        return false;
    }

}
