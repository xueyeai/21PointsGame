package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoom {
    private int capacity=0;
    private int roomNum=1;
    private int roomState=0;
    private List<Player> playerList =new ArrayList<>();
    private Cards cards=new Cards();

    public int getPlayerNum() {
        return playerList.size();
    }

    public enum ROOMSTATE{WAITTING,PREPARING, PREPARED,PLAYING};

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
        roomState+=1<<index;
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

    public void endGame(){

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
            endGame();
        }
        else{
            playerList.remove(player);
        }
    }

    private void dealCardsAtTheBeginning() throws OutOfCapacityException {
        for(int i=0;i<capacity;i++){
            dealCardsTo(playerList.get(i));
        }
    }

    private void dealCardsTo(Player player) throws OutOfCapacityException {
        player.addACard(cards.deal());
    }

    public int getRoomNum() {
        return roomNum;
    }

}
