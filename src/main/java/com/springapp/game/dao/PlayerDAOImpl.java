package com.springapp.game.dao;

import com.springapp.game.model.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xya on 2/22/14.
 */
public class PlayerDAOImpl implements PlayerDAO{
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public Player getPlayerById(int id) {
        Player player=jdbcTemplate.queryForObject(
                "select id,score,win,lose,deuce from player where id = ?",
                new Object[]{id},
                new RowMapper<Player>() {
                    @Override
                    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Player player=new Player();
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

    @Override
    public void addScore(int id, int score) {
        jdbcTemplate.update("update player set score=score+?  where id= ? ",score,id);
    }

    @Override
    public void subScore(int id, int score) {
        jdbcTemplate.update("update player set score=score-?  where id= ? ",score,id);
    }

    @Override
    public void addWin(int id) {

    }

    @Override
    public void subWin(int id) {

    }

    @Override
    public void addLose(int id) {

    }

    @Override
    public void subLose(int id) {

    }

    @Override
    public void addDeuce(int id) {

    }

    @Override
    public void subDeuce(int id) {

    }

    public void update(int id,String columnName,int num){
        jdbcTemplate.update("update player set "+columnName+" ="+columnName+String.valueOf(num));
    }
}
