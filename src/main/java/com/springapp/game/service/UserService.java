package com.springapp.game.service;

import com.springapp.game.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by xya on 2/16/14.
 */
public interface UserService {
    public User getUserById(int id);
    public void updateUserInfo(User user);
    public void login(User user);
    public void logout(User user);
    public User registerUser(int id,String nickname,String email,String city,String description);
    

}
