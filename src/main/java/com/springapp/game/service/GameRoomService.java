package com.springapp.game.service;

import com.springapp.game.dao.GameRoomDAO;
import com.springapp.game.model.GameRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xya on 2/25/14.
 */
@Service
public class GameRoomService {
    @Autowired
    private GameRoomDAO gameRoomDAO;


    public GameRoom getGameRoomById(int id) {
        return gameRoomDAO.getGameRoomByID(id);
    }


    public int getGameRoomNum() {
        return gameRoomDAO.getGameRoomNum();
    }
}
