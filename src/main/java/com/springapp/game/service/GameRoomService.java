package com.springapp.game.service;

import com.springapp.game.model.GameRoom;

/**
 * Created by xya on 2/25/14.
 */
public interface GameRoomService {
    public GameRoom getGameRoomById(int id);
    public int getGameRoomNum();
}
