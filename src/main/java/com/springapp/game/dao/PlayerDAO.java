package com.springapp.game.dao;

import com.springapp.game.model.Player;

/**
 * Created by xya on 2/22/14.
 */
public interface PlayerDAO {
    public Player getPlayerById(int id);
    public void addScore(int id,int score);
    public void subScore(int id,int score);
    public void addWin(int id);
    public void subWin(int id);
    public void addLose(int id);
    public void subLose(int id);
    public void addDeuce(int id);
    public void subDeuce(int id);
}
