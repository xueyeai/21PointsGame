package com.springapp.game.dao;

import com.springapp.game.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xya on 2/24/14.
 */
@Repository
public class AccountDAOImpl implements AccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account createAccount(String username, String password) {
        jdbcTemplate.update(" insert into user(username,password) values(?,?", username, password);
        return getAccountByUsername(username);
    }

    @Override
    public Account getAccountByUsername(String username) {
        Account account=jdbcTemplate.queryForObject(
                " select id,username,password,registerTime,lastLoginTime,lastLoginIP from account where username=? ",
                new Object[]{username},
                new RowMapper<Account>() {
                    @Override
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Account account=new Account();
                        account.setUsername(rs.getString("username"));
                        account.setPassword(rs.getString("password"));
                        account.setId(rs.getInt("id"));
                        account.setLastLoginTime(rs.getDate("lastLoginTime"));
                        account.setLastLoginIP(rs.getString("lastLoginIP"));
                        account.setRegisterTime(rs.getDate("registerTime"));
                       return account;
                    }
                });
        return account;
    }

    @Override
    public void changePassword(Account account) {
        jdbcTemplate.update(" update account set password=? where id=? ",account.getPassword(),account.getId());
    }

    @Override
    public void updateLoginInfo(Account account) {
        jdbcTemplate.update(" update account set lastLoginIP=?,lastLoginTime=?",account.getLastLoginIP(),account.getLastLoginTime());
    }

    @Override
    public int getMatchCount(String username, String password) {
        return jdbcTemplate.queryForObject(" select count(*) from account where username=? and password=?",Integer.class,username,password);
    }

    @Override
    public Boolean isRegistered(String username) {
        if(jdbcTemplate.queryForObject("select count(*) from account where username=?",Integer.class,username)==0){
            return true;
        }
        else{
            return false;
        }
    }
}
