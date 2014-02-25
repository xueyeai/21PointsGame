package com.springapp.game.service;

import com.springapp.game.model.Player;

/**
 * Created by xya on 2/25/14.
 */
public interface PlayerService {
    public Player getPlayerById(int id);
    public void updatePlayerData(Player player);

}
