package com.springapp.game.dao;

import com.springapp.game.model.Player;

/**
 * Created by xya on 2/22/14.
 */
public interface PlayerDAO {
    public Player createPlayer(int id);
    public Player getPlayerById(int id);
    public void updatePlayerData(Player player);
}
