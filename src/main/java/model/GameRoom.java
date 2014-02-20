package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoom {
    private int capacity=2;
    private int roomNum=1;
    private int roomState=0;
    private List<Player> playerList =new ArrayList<>();
    private List<Player> nextPlayerList=new ArrayList<>();
    private Cards cards=new Cards();
    public enum ROOMSTATE{WAITTING,PREPARING, PREPARED,PLAYING};

    public GameRoom(Cards cards){
        this.cards=cards;
    }

    public GameRoom(){}

    public GameRoom(int capacity){
        this.capacity=capacity;
    }

    public void joinRoom(Player player) throws Exception{
        if(playerList.size()<capacity){
            playerList.add(player);
            player.setRoomNum(roomNum);
        }
        else{
            throw new RoomIsFullException("Room Is Full!");
        }
    }

    public void prepared(Player player) throws Exception{
        if(getRoomState()==ROOMSTATE.PREPARING){
            changeRoomState(playerList.indexOf(player));
        }
        else{
            throw new RoomStateException("Room Is Not In An Legal State!");
        }

    }
    private void changeRoomState(int index) throws Exception {
        int x=1<<index;
        roomState+=x;
        if(roomState>(1<<capacity)){
            throw new RoomStateException("Room State Code Is Out Of Range!");
        }
    }
    private void changeRoomState(ROOMSTATE state){
        if (state==ROOMSTATE.PLAYING){
            roomState=1<<capacity;
        }
        if(state==ROOMSTATE.WAITTING){
            roomState=0;
        }
    }

    public void startGame() throws Exception{
        if(getRoomState()!=ROOMSTATE.PREPARED){
            throw new RoomIsNotPreparedException("Room Is Not Prepared!");
        }
        changeRoomState(ROOMSTATE.PLAYING);
        dealCardsAtTheBeginning();
    }

    public GameResult endGame(Player player) throws Exception{
        GameResult gameResult=endGame();
        gameResult.addLoser(player);
        return gameResult;
    }

    public GameResult endGame() throws Exception{
        sortPlayerList();
        nextPlayerList.clear();
        nextPlayerList.addAll(playerList);

        int loserPoint=0;
        int wonnerPoint=0;
        int theFirstBigThan21Index=-1;
        for(Player player:playerList){
            if(player.getCardsPoint()>21){
                theFirstBigThan21Index=playerList.indexOf(player);
                break;
            }
        }
        if(theFirstBigThan21Index>0){
            loserPoint=playerList.get(playerList.size()-1).getCardsPoint();
            wonnerPoint=playerList.get(theFirstBigThan21Index-1).getCardsPoint();
        }
        else if(theFirstBigThan21Index==0){
            loserPoint=playerList.get(playerList.size()-1).getCardsPoint();
            wonnerPoint=playerList.get(0).getCardsPoint();
        }
        else if(theFirstBigThan21Index==-1){
            loserPoint=playerList.get(0).getCardsPoint();
            wonnerPoint=playerList.get(playerList.size()-1).getCardsPoint();
        }

        cards.shuffle();
        playerList.clear();
        playerList.addAll(nextPlayerList);
        changeRoomState(ROOMSTATE.WAITTING);
        return getGameResult(loserPoint, wonnerPoint);
    }

    private GameResult getGameResult(int loserPoint, int wonnerPoint) {
        GameResult gameResult=new GameResult();
        if(loserPoint==wonnerPoint){
            gameResult.setDeduce(playerList);
        }
        else {
            for(Player player:playerList){
                if (player.getCardsPoint()==wonnerPoint){
                    nextPlayerList.remove(player);
                    nextPlayerList.set(0,player);
                    gameResult.addWonner(player);
                }
                else if(player.getCardsPoint()==loserPoint){
                    gameResult.addLoser(player);
                }
                else{
                    gameResult.addDeuce(player);
                }
            }
        }
        return gameResult;
    }

    private void sortPlayerList() {
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getCardsPoint() > o2.getCardsPoint()) {
                    return 1;
                } else if (o1.getCardsPoint() < o2.getCardsPoint()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public ROOMSTATE getRoomState(){
        if(playerList.size()<capacity){
            return ROOMSTATE.WAITTING;
        }
        else if(roomState==(1<<capacity)){
            return ROOMSTATE.PLAYING;
        }
        else if(roomState==((1<<capacity)-1)){
            return ROOMSTATE.PREPARED;
        }
        return ROOMSTATE.PREPARING;
    }

    public void leftRoom(Player player) throws Exception {
        player.initTempVariable();
        if(getRoomState()==ROOMSTATE.PLAYING){
            playerList.remove(player);
            endGame(player);
        }
        else{
            playerList.remove(player);
        }
    }

    private void dealCardsAtTheBeginning() throws Exception {
        for(int i=0;i<2;i++){
            for(Player player:playerList){
                dealCardsTo(player);
            }
        }
    }

    public int dealCardsTo(Player player) throws Exception {
        if(getRoomState()==ROOMSTATE.PLAYING){
            player.addACard(cards.deal());
            return player.getCardsPoint();
        }
        else{
            throw new GameNotStartException("Game Is Not Start So You Can Not Deal Cards!!");
        }
    }

    public int getRoomNum() {
        return roomNum;
    }

    public int getPlayerNum() {
        return playerList.size();
    }

}
