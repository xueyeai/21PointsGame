package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoom {
    private List<Player> playerList =new ArrayList<>();
    private Cards cards=new Cards();
    private int step=0;
    private int state=0;
    private Player gameLoser;

    public void joinRoom(Player player) throws Exception{
        if(playerList.size()<2){
            playerList.add(player);
            changeState(playerList.size());
        }
        else{
            throw new RoomIsFullException("Room Is Full!");
        }
    }

    public void leftRoom(Player player) throws Exception {
        if(state==0){
            throw new RoomIsEmptyException("Room Is Empty!");
        }
        else if(state<3){
            playerList.remove(player);
            state=playerList.size();
        }
        else{
            endGame(player);
        }
    }

    public void startGame() throws Exception{
        if(state==2){
            dealCardsAtTheBeginning();
            changeState(3);
        }
        else{
            throw new RoomIsNotPreparedException("Room Is Not Prepared!");
        }
    }

    public void endGame(Player player) throws Exception{
        if(state==3){
            gameLoser=player;
            changeState(2);
            leftRoom(player);
        }
    }

    public void endGame() throws Exception{
        if(state==3){
            int result=playerList.get(0).calculatePoints()-playerList.get(1).calculatePoints();
            if(result>0){
                gameLoser=playerList.get(1);
            }
            else if(result<0){
                gameLoser=playerList.get(0);
            }
            gameLoser=null;
            changeState(2);
        }
    }

    private void playing(int step) throws Exception{
        if (step==0){
            dealCardsAtTheBeginning();
        }
        else{
            dealCardsTo(playerList.get((step++)%2&0));
        }
    }

    private void dealCardsAtTheBeginning() throws OutOfCapacityException {
        for(int i=0;i<2;i++){
            dealCardsTo(playerList.get(i));
        }
    }

    private void dealCardsTo(Player player) throws OutOfCapacityException {
        player.addACard(cards.deal());
        ++step;
    }
    private void changeState(int state) {
        this.state=state;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getState() {
        return state;
    }

    public Player getGameLoser() {
        return gameLoser;
    }
}
