package model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by xya on 2/19/14.
 */
public class PlayerTest {
    private Player player1=new Player(new User());
    private GameRoom gameRoom=new GameRoom(2);

    @Test
    public void could_player_will_return_the_right_roomNum_after_leftRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.leftRoom(player1);
        assertThat(player1.getRoomNum(), is(0));
    }

    @Test
    public void could_player_will_return_the_right_roomNum_after_joinRoom() throws Exception{
        gameRoom.joinRoom(player1);
        assertThat(player1.getRoomNum(),is(gameRoom.getRoomNum()));
    }
}
