package com.springapp.game.controller;

import com.springapp.game.model.Account;
import com.springapp.game.model.Player;
import com.springapp.game.model.User;
import com.springapp.game.service.AccountService;
import com.springapp.game.service.PlayerService;
import com.springapp.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Created by xya on 2/25/14.
 */
@Controller
@RequestMapping("/account")
@SessionAttributes(value = {"account", "user", "player"}, types = {Account.class, User.class, Player.class})
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping("/login")
    public ModelAndView loginPage(HttpServletRequest req) {
        if (req.getSession(true).getAttribute("user") == null) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("redirect:/manage/main");
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView checkPage(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (!accountService.getMatchCount(username, password)) {
            return new ModelAndView("login", "error", "error username or password!");
        }
        Account account = accountService.getAccountByUsername(username);
        User user = userService.getUserById(account.getId());
        Player player = playerService.getPlayerById(account.getId());

        ModelAndView view = new ModelAndView("redirect:/manage/main");
        view.addObject("user", user);
        view.addObject("player", player);
        view.addObject("account", account);
        return view;
    }

    @RequestMapping("/logout")
    public ModelAndView logoutPage(HttpServletRequest req) {
        req.getSession().invalidate();
        return new ModelAndView("login");
    }

    @RequestMapping("/register")
    public ModelAndView registerPage(HttpServletRequest req) {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/registerCheck", method = RequestMethod.POST)
    public ModelAndView registerCheck(HttpServletRequest req) {
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String username = req.getParameter("username");

        if (!(password1.equals(password2))) {
            return new ModelAndView("register", "error", "Entered passwords differ!");
        }

        if (accountService.isRegistered(username)) {
            return new ModelAndView("register", "error", "Username is already used!");
        }

        if (!checkRegisterForm(username, password1)) {
            return new ModelAndView("register", "error", "You information is incorrectly formatted!");
        }

        Account account = accountService.registerAccount(username, password1);
        User user = userService.registerUser(account.getId(), "", "", "", "");
        Player player = playerService.registerPlayer(account.getId());
        ModelAndView view = new ModelAndView("redirect:/manage/main");
        view.addObject("user", user);
        view.addObject("player", player);
        view.addObject("account", account);
        return view;
    }

    private Boolean checkRegisterForm(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9].{6,15}");
        Pattern passwordPattern = Pattern.compile("[a-zA-Z0-9@#$%].{6,20}");
        if (!usernamePattern.matcher(username).matches()) {
            return false;
        }
        if (!passwordPattern.matcher(password).matches()) {
            return false;
        }
        return true;
    }
}
