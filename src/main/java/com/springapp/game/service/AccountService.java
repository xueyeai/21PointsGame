package com.springapp.game.service;


import com.springapp.game.dao.AccountDAO;
import com.springapp.game.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xya on 2/25/14.
 */
@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    public void updateLoginInfo(Account account) {
        accountDAO.updateLoginInfo(account);
    }


    public int changePassword(Account account, String password) {
        if (checkPasswordForm(password)) {
            account.setPassword(password);
            accountDAO.changePassword(account);
        } else {
            return 0;
        }
        return 1;
    }

    private boolean checkPasswordForm(String password) {
        if (password.length() < 6) {
            return false;
        } else if (password.length() > 15) {
            return false;
        }
        return true;
    }


    public Boolean getMatchCount(String username, String password) {
        if (accountDAO.getMatchCount(username, password) == 1) {
            return true;
        }
        return false;
    }


    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }


    public boolean isRegistered(String username) {
        return accountDAO.isRegistered(username);
    }


    public Account registerAccount(String username, String password) {
        return accountDAO.createAccount(username, password);
    }


}
