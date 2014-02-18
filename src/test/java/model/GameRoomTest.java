package model;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoomTest extends TestCase {

    private Player player1=new Player();
    private Player player2=new Player();
    GameRoom2 gameRoom2 =new GameRoom2();

    @Test
    public void should_a_player_joinRoom() throws Exception{
        gameRoom2.joinRoom(player1);
        assertThat(gameRoom2.getPlayers());
    }


}
