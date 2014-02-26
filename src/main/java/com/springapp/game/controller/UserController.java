package com.springapp.game.controller;

import com.springapp.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xya on 2/25/14.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;


}
