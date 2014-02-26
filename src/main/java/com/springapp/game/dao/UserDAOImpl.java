package com.springapp.game.dao;

import com.springapp.game.model.User;
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
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User createUser(int id, String nickname, String email, String city, String description) {
        jdbcTemplate.update("insert into user(id,nickname,email,city,description) values(?,?,?,?,?)",id,nickname,email,city,description);
        return getUserById(id);
    }

    @Override
    public User getUserById(int id) {
        User user=jdbcTemplate.queryForObject(
                "select id,nickname,email,city,description from user where id=?",new Object[]{id},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user=new User();
                        user.setId(rs.getInt("id"));
                        user.setNickname(rs.getString("nickname"));
                        user.setDescription(rs.getString("description"));
                        user.setEmail(rs.getString("email"));
                        user.setCity(rs.getString("city"));
                        return user;
                    }
                });
        return user;
    }

    @Override
    public void updateUserInfo(User user) {
        jdbcTemplate.update("update user set nickname=?,email=?,city=?,description=? where id=?",
                user.getNickname(),user.getEmail(),user.getCity(),user.getDescription(),user.getId());
    }

    @Override
    public void changeOnlineState(User user, int online) {
        jdbcTemplate.update("update user set online=? where id=?",online,user.getId());
    }
}
