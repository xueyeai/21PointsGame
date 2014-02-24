package com.springapp.game.model;

import com.springapp.game.exception.GameNotStartException;
import com.springapp.game.exception.RoomIsFullException;
import com.springapp.game.exception.RoomIsNotPreparedException;
import com.springapp.game.exception.RoomStateException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoomTest {
    private Player player1=new Player(1);
    private Player player2=new Player(1);
    GameRoom gameRoom =new GameRoom(2);
    int roomNum=0;

    @Before
    public void setUp(){
        roomNum= gameRoom.getRoomNo();
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
            gameRoom.joinRoom(new Player(1));
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

    @Test
    public void could_startGame_deal_cards_at_the_beginning() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player1);
        gameRoom.prepared(player2);
        gameRoom.startGame();
        assertThat(player1.getCards().size(),is(2));
        assertThat(player2.getCards().size(),is(2));
    }

    @Test
    public void could_endGame_works_after_startGame() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        assertThat(gameRoom.endGame(),is(GameResult.class));
    }

    @Test
    public void could_endGame_works_after_startGame_and_dealCardsTo() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        gameRoom.dealCardsTo(player1);
        gameRoom.dealCardsTo(player2);
        assertThat(gameRoom.endGame(),is(GameResult.class));
    }

    @Test
    public void could_endGame_begin_startGame_throw_GameNotStartException() throws Exception{
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        try{
            gameRoom.endGame();
        }catch (Exception e){
            if(e instanceof GameNotStartException){
                return;
            }
            fail();
        }
    }

    @Test
    public void could_gameResult_right_when_a_player_win_with_two_players_point_less_than_21() throws Exception{
        Cards cards=mock(Cards.class);
        when(cards.deal()).
                thenReturn(new Card(Card.FLOWER.CLUB, Card.POINT.ACE)).
                thenReturn(new Card(Card.FLOWER.CLUB,Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.EIGHT)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.JACK)).
                thenReturn(new Card(Card.FLOWER.HEART,Card.POINT.THREE));
        gameRoom =new GameRoom(1,cards);
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        gameRoom.dealCardsTo(player1);
        GameResult gameResult= gameRoom.endGame();
        assertThat(gameResult.getWonners().contains(player2),is(true));
        assertThat(gameResult.getLosers().contains(player1),is(true));
    }

    @Test
    public void could_gameResult_right_when_a_player_win_with_two_players_point_more_than_21() throws Exception{
        Cards cards=mock(Cards.class);
        when(cards.deal()).
                thenReturn(new Card(Card.FLOWER.CLUB, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.CLUB,Card.POINT.JACK)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.JACK)).
                thenReturn(new Card(Card.FLOWER.HEART,Card.POINT.THREE)).
                thenReturn(new Card(Card.FLOWER.HEART,Card.POINT.TWO));
        gameRoom =new GameRoom(1,cards);
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        gameRoom.dealCardsTo(player1);
        gameRoom.dealCardsTo(player2);
        GameResult gameResult= gameRoom.endGame();
        assertThat(gameResult.getWonners().contains(player2),is(true));
        assertThat(gameResult.getLosers().contains(player1),is(true));
    }


    @Test
    public void could_gameResult_right_when_two_players_deuce_with_two_players_point_less_than_21() throws Exception{
        Cards cards=mock(Cards.class);
        when(cards.deal()).
                thenReturn(new Card(Card.FLOWER.CLUB, Card.POINT.ACE)).
                thenReturn(new Card(Card.FLOWER.DIAMOND,Card.POINT.ACE)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.NINE)).
                thenReturn(new Card(Card.FLOWER.CLUB, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.SPADE, Card.POINT.ACE));

        gameRoom =new GameRoom(1,cards);
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        gameRoom.dealCardsTo(player1);
        GameResult gameResult= gameRoom.endGame();
        assertThat(gameResult.getDeuces().contains(player2),is(true));
        assertThat(gameResult.getDeuces().contains(player1),is(true));
    }

    @Test
    public void could_gameResult_right_when_two_players_deuce_with_two_players_point_more_than_21()  throws Exception{
        Cards cards=mock(Cards.class);
        when(cards.deal()).
                thenReturn(new Card(Card.FLOWER.CLUB, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.CLUB,Card.POINT.JACK)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.JACK)).
                thenReturn(new Card(Card.FLOWER.HEART,Card.POINT.THREE)).
                thenReturn(new Card(Card.FLOWER.CLUB,Card.POINT.THREE));
        gameRoom =new GameRoom(1,cards);
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        gameRoom.dealCardsTo(player1);
        gameRoom.dealCardsTo(player2);
        GameResult gameResult= gameRoom.endGame();
        assertThat(gameResult.getDeuces().contains(player2),is(true));
        assertThat(gameResult.getDeuces().contains(player1),is(true));
    }

    @Test
    public void could_gameResult_right_when_two_players_deuce_with_two_players_point_equal_21()  throws Exception{
        Cards cards=mock(Cards.class);
        when(cards.deal()).
                thenReturn(new Card(Card.FLOWER.CLUB, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.CLUB,Card.POINT.SEVEN)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.SEVEN)).
                thenReturn(new Card(Card.FLOWER.HEART,Card.POINT.ACE)).
                thenReturn(new Card(Card.FLOWER.HEART,Card.POINT.SEVEN));
        gameRoom =new GameRoom(1,cards);
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        gameRoom.dealCardsTo(player1);
        gameRoom.dealCardsTo(player2);
        GameResult gameResult= gameRoom.endGame();
        assertThat(gameResult.getDeuces().contains(player2),is(true));
        assertThat(gameResult.getDeuces().contains(player1),is(true));
    }

    @Test
    public void could_gameResult_right_when_a_player_point_more_than_21_and_another_less_than_21()  throws Exception{
        Cards cards=mock(Cards.class);
        when(cards.deal()).
                thenReturn(new Card(Card.FLOWER.CLUB, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.CLUB,Card.POINT.EIGHT)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.KING)).
                thenReturn(new Card(Card.FLOWER.DIAMOND, Card.POINT.SEVEN)).
                thenReturn(new Card(Card.FLOWER.HEART,Card.POINT.THREE)).
                thenReturn(new Card(Card.FLOWER.CLUB,Card.POINT.SIX));
        gameRoom =new GameRoom(1,cards);
        gameRoom.joinRoom(player1);
        gameRoom.joinRoom(player2);
        gameRoom.prepared(player2);
        gameRoom.prepared(player1);
        gameRoom.startGame();
        gameRoom.dealCardsTo(player1);
        gameRoom.dealCardsTo(player2);
        GameResult gameResult= gameRoom.endGame();
        assertThat(gameResult.getWonners().contains(player2),is(true));
        assertThat(gameResult.getLosers().contains(player1),is(true));
    }

}
