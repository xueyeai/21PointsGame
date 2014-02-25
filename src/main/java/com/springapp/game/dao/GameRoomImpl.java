package com.springapp.game.dao;

import com.springapp.game.model.GameRoom;
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
public class GameRoomImpl implements GameRoomDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GameRoom getGameRoomByID(int id) {
        GameRoom gameRoom=jdbcTemplate.queryForObject(
                "select id,capacity from gameRoom where id=?",new Object[]{id},
                new RowMapper<GameRoom>() {
                    @Override
                    public GameRoom mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new GameRoom(rs.getInt("id"),rs.getInt("capacity"));
                    }
                });
        return gameRoom;
    }

}