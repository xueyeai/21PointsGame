package com.springapp.game.dao;

import com.springapp.game.model.Account;

/**
 * Created by xya on 2/24/14.
 */
public interface AccountDAO {
    public Account createAccount(String username,String password);
    public Account getAccountByUsername(String username);
    public void changePassword(Account account);
    public void updateLoginInfo(Account account);
    public int getMatchCount(String username,String password);
    public Boolean isRegistered(String username);

}
