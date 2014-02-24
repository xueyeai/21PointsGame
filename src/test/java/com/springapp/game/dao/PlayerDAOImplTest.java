package com.springapp.game.dao;

import com.springapp.game.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.jdbc.datasource.*;

/**
 * Created by xya on 2/22/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/beans.xml")
public class PlayerDAOImplTest{

    @Autowired
    public PlayerDAO playerDAO;

    @Test
    public void test(){
    }

}
