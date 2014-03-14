package com.springapp.game.controller;

import com.springapp.game.model.Account;
import com.springapp.game.model.Player;
import com.springapp.game.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xya on 2/26/14.
 */
@Controller
@RequestMapping("/manage")
@SessionAttributes(value = {"account", "user", "player"}, types = {Account.class, User.class, Player.class})

public class ManageController {
    @RequestMapping("/main")
    public ModelAndView mainPage(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return new ModelAndView("main", "user", user.getNickname());
    }

}
