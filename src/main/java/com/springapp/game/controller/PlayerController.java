package com.springapp.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xya on 2/20/14.
 */
@Controller
public class PlayerController {

    @RequestMapping("/player")
    public ModelAndView getPlayerState(@PathVariable String userId){
        ModelAndView view=new ModelAndView("player");
        view.addObject("name","xxx");
        return view;
    }

}
