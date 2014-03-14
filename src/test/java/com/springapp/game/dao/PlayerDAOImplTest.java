package com.springapp.game.dao;

import com.springapp.game.model.Account;
import com.springapp.game.model.Player;
import com.springapp.game.model.User;
import com.springapp.game.service.AccountService;
import com.springapp.game.service.PlayerService;
import com.springapp.game.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpSession;


/**
 * Created by xya on 2/22/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/beans.xml")
public class PlayerDAOImplTest{


    @Autowired
    public AccountService accountService;
    @Autowired
    public UserService userService;
    @Autowired
    public PlayerService playerService;
   @Test
    public void test(){
       Account account=accountService.registerAccount("usernamexx","password");
       User user=userService.registerUser(account.getId(),"nick","aaa@a","","");
       Player player=playerService.registerPlayer(account.getId());
   }

}
