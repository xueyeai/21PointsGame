package com.springapp.game.service;


import com.springapp.game.dao.AccountDAO;
import com.springapp.game.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.tools.jar.resources.jar;

/**
 * Created by xya on 2/25/14.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void updateLoginInfo(Account account) {
        accountDAO.updateLoginInfo(account);
    }

    @Override
    public int changePassword(Account account,String password) {
        if(checkPasswordForm(password)){
            account.setPassword(password);
            accountDAO.changePassword(account);
        }else{
            return 0;
        }
        return 1;
    }

    private boolean checkPasswordForm(String password) {
        if(password.length()<6){
            return false;
        }
        else if(password.length()>15){
            return false;
        }
        return true;
    }

    @Override
    public Boolean getMatchCount(String username, String password) {
        if(accountDAO.getMatchCount(username,password)==1){
            return true;
        }
        return false;
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    @Override
    public boolean isRegistered(String username) {
        return accountDAO.isRegistered(username);
    }

    @Override
    public Account register(String username, String password) {
        return accountDAO.createAccount(username,password);
    }


}
