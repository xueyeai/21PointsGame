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
    public Player createPlayer(int id) {
        jdbcTemplate.execute("insert into player(id) values(id)");
        return getPlayerById(id);
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
        add(id,"totalScore",score);
    }

    @Override
    public void subScore(int id, int score) {
        sub(id, "totalScore", score);
    }

    @Override
    public void addWin(int id) {
        add(id,"win",1);
    }

    @Override
    public void subWin(int id) {
        sub(id, "win", 1);
    }

    @Override
    public void addLose(int id) {
        add(id,"lose",1);
    }

    @Override
    public void subLose(int id) {
        sub(id, "lose", 1);
    }

    @Override
    public void addDeuce(int id) {
        add(id,"deuce",1);
    }

    @Override
    public void subDeuce(int id) {
        sub(id, "deuce", 1);
    }

    private void add(int id,String columnName,int num){
        String sql=String.format("update player set %s=%s+%d where id = ?",columnName,columnName,num);
        jdbcTemplate.update(sql,id);
    }

    private void sub(int id,String columnName,int num){
        String sql=String.format("update player set %s=%s-%d where id = ?",columnName,columnName,num);
        jdbcTemplate.update(sql,id);
    }

}
