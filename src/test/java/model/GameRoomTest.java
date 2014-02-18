package model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoomTest{

    private Player player1=new Player();
    private Player player2=new Player();
    GameRoom gameRoom =new GameRoom();

    @Test
    public void could_one_player_joinRoom() throws Exception{
        gameRoom.joinRoom(player1);
        Boolean requirement1=gameRoom.getPlayerList().size()==1;
        Boolean requirement2=gameRoom.getPlayerList().contains(player1);
        assertThat(requirement1&requirement2,is(true));
    }

    @Test
    public void could_two_players_joinRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        assertThat(gameRoom.getPlayerList().size()==2,is(true));
    }

    @Test
    public void could_RoomIsFullException_works() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        try {
            gameRoom.joinRoom(new Player());
        }catch (Exception e){
            if(e instanceof RoomIsFullException){
                return;
            }
            fail();
        }
    }

    @Test
    public void could_one_player_leftRoom_when_room_is_full() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.leftRoom(player1);
        Boolean requirement1=(gameRoom.getPlayerList().size()==1);
        Boolean requirement2=(gameRoom.getPlayerList().contains(player2));
        assertThat(requirement1&requirement2,is(true));
    }

    @Test
    public void could_two_players_leftRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.leftRoom(player1);
        gameRoom.leftRoom(player2);
        assertThat(gameRoom.getPlayerList().size()==0,is(true));
    }

    @Test
    public void could_a_player_joinRoom_after_another_player_leftRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.leftRoom(player1);
        gameRoom.joinRoom(player2);
        Boolean requirement1=(gameRoom.getPlayerList().size()==1);
        Boolean requirement2=(gameRoom.getPlayerList().contains(player2));
        assertThat(requirement1&requirement2,is(true));
    }




}
