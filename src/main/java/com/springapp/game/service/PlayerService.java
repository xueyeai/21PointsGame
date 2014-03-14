package com.springapp.game.service;

import com.springapp.game.dao.PlayerDAO;
import com.springapp.game.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xya on 2/25/14.
 */
@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    public Player getPlayerById(int id) {
        return playerDAO.getPlayerById(id);
    }

    public void updatePlayerData(Player player) {
        playerDAO.updatePlayerData(player);
    }

    public Player registerPlayer(int id) {
        return playerDAO.createPlayer(id);
    }
}
