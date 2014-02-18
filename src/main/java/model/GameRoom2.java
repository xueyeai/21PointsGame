package model;

import java.util.List;
import java.util.Map;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoom2 {
    private Player oridinaryPlayer=new Player();
    private Player makerPlayer=new Player();
    private Cards cards=new Cards();
    private int step=0;
    private STATE state=STATE.EMPTY;


    private enum GAMERESULT {DEUCE,MAKERWON,ORIDINARYWON};
    private GAMERESULT gameResult;

    public enum STATE {
        EMPTY(0),WAITTING(1),PREPARED(2),PLAYING(3);
        private int stateCode;
        private STATE(int stateCode){
            this.stateCode=stateCode;
        }
    }

    public void joinRoom(Player player) throws Exception{
        if (state.stateCode==0){
            makerPlayer=player;
        }
        else if(state.stateCode==1){
            oridinaryPlayer=player;
        }
        else{
            throw new RoomIsFullException("Room Is Full!");
        }
    }

    public void leftRoom(Player player) throws Exception {
        if(state.stateCode==0){
            throw new RoomIsEmptyException("Room Is Empty!");
        }
        if(state.stateCode==1){
            makerPlayer=new Player();
            changeState(STATE.EMPTY);
        }
        if(state.stateCode==2){
            if (player==oridinaryPlayer){
                oridinaryPlayer=new Player();
            }
            else {
                makerPlayer=oridinaryPlayer;
                oridinaryPlayer=new Player();
            }
            changeState(STATE.WAITTING);
        }
        if(state.stateCode==3){
            endGame(player);
        }
    }

    public void startGame() throws Exception{
        if(state.stateCode==2){
            playing(step);
            changeState(STATE.PLAYING);
        }
        else{
            throw new RoomIsNotPreparedException("Room Is Not Prepared!");
        }
    }

    public void endGame(Player player) throws Exception{
        if(state.stateCode==3){
            if (player==makerPlayer){
                gameResult=GAMERESULT.ORIDINARYWON;
            }
            else{
                gameResult=GAMERESULT.MAKERWON;
            }
            changeState(STATE.PREPARED);
            leftRoom(player);
        }
    }

    public void endGame() throws Exception{
        if(state.stateCode==3){
            changeState(STATE.PREPARED);
            int result=makerPlayer.calculatePoints()-oridinaryPlayer.calculatePoints();
            if(result==0){
                gameResult = GAMERESULT.DEUCE;
            }
            else if(result>0){
                gameResult = GAMERESULT.MAKERWON;
            }
            else if(result<0){
                gameResult = GAMERESULT.ORIDINARYWON;
            }
        }
        gameResult = GAMERESULT.DEUCE;
    }

    private void playing(int step) throws Exception{
        if (step==0){
            dealCardsAtTheBeginning();
        }
        else if((step%2)!=0){
            dealCardsTo(makerPlayer);
        }
        else if((step&1)!=0){
            dealCardsTo(oridinaryPlayer);
        }
    }

    private void dealCardsAtTheBeginning() throws OutOfCapacityException {
        for(int i=0;i<2;i++){
            dealCardsTo(makerPlayer);
            dealCardsTo(oridinaryPlayer);
        }
    }

    private void dealCardsTo(Player player) throws OutOfCapacityException {
        player.addACard(cards.deal());
        ++step;
    }
    private void changeState(STATE state) {
        this.state=state;
    }




}
