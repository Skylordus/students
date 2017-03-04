package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IUserService {
    void register(String user_role,
                  String user_name,
                  String user_surname,
                  String user_patronymic,
                  String user_birthdate,
                  String user_login,
                  String user_password,
                  String user_email);
    User authorize(String login, String password);
}
