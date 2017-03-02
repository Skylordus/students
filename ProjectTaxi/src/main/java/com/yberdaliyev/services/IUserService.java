package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IUserService {
    void register(HttpServletRequest request);
    User authorize(String login, String password);
}
