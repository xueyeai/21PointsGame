package com.springapp.game.service;

import com.springapp.game.model.Account;

/**
 * Created by xya on 2/25/14.
 */
public interface AccountService {
    public void updateLoginInfo(Account account);
    public int changePassword(Account account,String password);
    public Boolean getMatchCount(String username,String password);
    public Account getAccountByUsername(String username);
    public boolean isRegistered(String username);
    public Account register(String username,String password);
}
