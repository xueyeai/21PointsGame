package com.springapp.game.service;

import com.springapp.game.dao.PlayerDAO;
import com.springapp.game.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xya on 2/25/14.
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    @Override
    public Player getPlayerById(int id) {
        return playerDAO.getPlayerById(id);
    }

    @Override
    public void updatePlayerData(Player player) {
        playerDAO.updatePlayerData(player);
    }

    @Override
    public Player registerPlayer(int id) {
        return playerDAO.createPlayer(id);
    }
}
