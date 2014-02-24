package com.springapp.game.dao;

import com.springapp.game.model.Player;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by xya on 2/22/14.
 */
@WebAppConfiguration
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class PlayerDAOImplTest{

    @Autowired
    protected Player player;

    @Test
    public void test(){
        System.out.printf(String.valueOf(this.player.getScore()));
    }
}
