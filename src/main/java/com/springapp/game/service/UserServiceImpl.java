package com.springapp.game.service;

import com.springapp.game.dao.UserDAO;
import com.springapp.game.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xya on 2/25/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUserInfo(User user) {
        userDAO.updateUserInfo(user);
    }

    @Override
    public void login(User user) {
        userDAO.changeOnlineState(user,1);
    }

    @Override
    public void logout(User user) {
        userDAO.changeOnlineState(user,0);
    }

    @Override
    public User registerUser(int id, String nickname, String email, String city, String description) {
        return userDAO.createUser(id,nickname,email,city,description);
    }
}
