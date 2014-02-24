package com.springapp.game.controller;

import com.springapp.game.dao.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xya on 2/20/14.
 */
@Controller
public class PlayerController {

    @Autowired
    private PlayerDAO playerDAO;

    @RequestMapping("/player")
    public ModelAndView addScore(){
        ModelAndView view=new ModelAndView("player");
        playerDAO.addScore(1,1);
        view.addObject("name","xxx");
        return view;
    }

    public PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
}
