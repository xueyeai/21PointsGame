package model;

import dao.UserDAO;
import dao.UserDAOImp;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xya on 2/15/14.
 */
public class Player {
    private List<Card> cards=new ArrayList<>();
    private String userName;
    private int userId;
    private  int userScore;
    private int tempScore=0;
    private int hit=0;
    private int roomNum=0;

    public Player(User user){
        this.userName=user.getUserName();
        this.userId=user.getId();
        this.userScore=user.getScore();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addACard(Card card){
        cards.add(card);
    }

    public int getUserScore() {
        return userScore;
    }

    public int getCardsPoint() {
        int point=0;
        for(Card card:cards){
            point+=card.getPoint();

        }
        return point;
    }

    public int getTempScore() {
        return tempScore;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void addTempScore(int tempScore) {
        this.tempScore += tempScore;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void initTempVariable() {
        setRoomNum(0);
        setHit(0);
        setTempScore(0);
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setTempScore(int tempScore) {
        this.tempScore = tempScore;
    }
}
