package com.springapp.game.model;

import java.util.Date;

/**
 * Created by xya on 2/16/14.
 */
public class User {
    private String userName;
    private int id;
    private String passwd;
    private Date lastLoginTime;
    private Date registerLoginTime;

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getRegisterLoginTime() {
        return registerLoginTime;
    }

    public void setRegisterLoginTime(Date registerLoginTime) {
        this.registerLoginTime = registerLoginTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
