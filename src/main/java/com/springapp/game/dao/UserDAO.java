package com.springapp.game.dao;

import com.springapp.game.model.User;

/**
 * Created by xya on 2/22/14.
 */
public interface UserDAO {
    public User createUser(String userName,String nickname,String email,String city,String description);
    public User getUserById(int id);
    public void updateInfo(String email,String city,String description);
    public void login();
    public void logout();
}
