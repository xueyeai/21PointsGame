package com.springapp.game.service;

import com.springapp.game.model.User;

/**
 * Created by xya on 2/16/14.
 */
public interface UserService {
    public User getUserByUsername(String username);
    public void updateUserInfo(User user);
    public void login(User user);
    public void logout(User user);
    

}
