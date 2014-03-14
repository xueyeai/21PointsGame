package com.springapp.game.dao;

import com.springapp.game.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xya on 2/22/14.
 */
@Repository
public class PlayerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Player createPlayer(int id) {
        jdbcTemplate.update(" insert into player(id) values(?) ", id);
        return getPlayerById(id);
    }

    public Player getPlayerById(int id) {
        Player player = jdbcTemplate.queryForObject(
                "select id,score,win,lose,deuce from player where id = ?",
                new Object[]{id},
                new RowMapper<Player>() {
                    @Override
                    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Player player = new Player();
                        player.setId(rs.getInt("id"));
                        player.setScore(rs.getInt("score"));
                        player.setDeuce(rs.getInt("deuce"));
                        player.setWin(rs.getInt("win"));
                        player.setLose(rs.getInt("lose"));
                        return player;
                    }
                });
        return player;
    }

    public void updatePlayerData(Player player) {
        jdbcTemplate.update("update player set score=?, win=?, lose=?, deuce=?", player.getScore(), player.getWin(), player.getLose(), player.getDeuce());
    }

}
