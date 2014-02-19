package model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoomTest{
    private User user1=new User();
    private User user2=new User();
    private Player player1=new Player(user1);
    private Player player2=new Player(user2);
    GameRoom gameRoom =new GameRoom(2);
    int roomNum=0;

    @Before
    public void setUp(){
        roomNum=gameRoom.getRoomNum();
    }

    @Test
    public void could_one_player_joinRoom() throws Exception{
        gameRoom.joinRoom(player1);
        assertThat(gameRoom.getPlayerNum(),is(1));
    }

    @Test
    public void could_2_players_joinRoom() throws Exception{

        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        assertThat(gameRoom.getPlayerNum(),is(2));
    }

    @Test
    public void could_RoomIsFullException_works() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        try {
            gameRoom.joinRoom(new Player(new User()));
        }catch (Exception e){
            if(e instanceof RoomIsFullException){
                return;
            }
            fail();
        }
    }

    @Test
    public void could_player_will_return_the_right_roomNum_after_leftRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.leftRoom(player1);
        assertThat(player1.getRoomNum(), is(0));
    }

    @Test
    public void could_player_will_return_the_right_roomNum_after_joinRoom() throws Exception{
        gameRoom.joinRoom(player1);
        assertThat(player1.getRoomNum(),is(roomNum));
    }


    @Test
    public void could_one_player_leftRoom_when_room_is_full() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.leftRoom(player1);
        assertThat(gameRoom.getPlayerNum(),is(1));
    }

    @Test
    public void could_two_players_leftRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.leftRoom(player1);
        gameRoom.leftRoom(player2);
        assertThat(gameRoom.getPlayerNum(),is(0));
    }

    @Test
    public void could_a_player_joinRoom_after_another_player_leftRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.leftRoom(player1);
        gameRoom.joinRoom(player2);
        assertThat(gameRoom.getPlayerNum(),is(1));
    }

    @Test
    public void could_a_player_joinRoom_after_leftRoom() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.leftRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.joinRoom(player1);
        assertThat(gameRoom.getPlayerNum(),is(2));
    }

    @Test
    public void could_startGame_when_players_all_prepared() throws Exception {
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player1);
        gameRoom.prepared(player2);
        gameRoom.startGame();
        assertThat(gameRoom.getRoomState(),is(GameRoom.ROOMSTATE.PLAYING));
    }

    @Test
    public void could_startGame_throws_RoomIsNotPrepared_Exception_when_players_not_prepared() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player1);
        try{
            gameRoom.startGame();
        }catch (Exception e){
            if(e instanceof RoomIsNotPreparedException){
                return;
            }
            fail();
        }
    }

    @Test
    public void could_prepared_throws_RoomStateException_Exception_when_there_is_only_one_player() throws Exception{
        gameRoom.joinRoom(player1);
        try{
            gameRoom.prepared(player1);
        }catch (Exception e){
            if(e instanceof RoomStateException){
                return;
            }
            fail();
        }
    }




}
