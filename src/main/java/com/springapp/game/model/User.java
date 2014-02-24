package com.springapp.game.model;

import java.util.Date;

/**
 * Created by xya on 2/16/14.
 */
public class User {
    private int id;
    private String username;
    private String nickname;
    private String email;
    private String description;
    private int online;

    public User(int id, String username, String nickname, String email, String description, int online) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.description = description;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}
