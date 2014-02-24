package com.springapp.game.dao;

import com.springapp.game.model.Account;

/**
 * Created by xya on 2/24/14.
 */
public interface AccountDAO {
    public void createAccount();
    public Account getAccountById();
    public void changePassword();
    
}
