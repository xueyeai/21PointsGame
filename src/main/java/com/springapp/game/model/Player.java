package com.springapp.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xya on 2/15/14.
 */
public class Player {
    private List<Card> cards=new ArrayList<>();
    private String username="";
    private int id;
    private int score;
    private int win=0;
    private int lose=0;
    private int deuce=0;
    private int tempScore=0;
    private int roomNo =0;

    public Player(){
        initTempVariable();
    }

    public void initTempVariable() {
        setRoomNo(0);
        setTempScore(0);
    }


    public List<Card> getCards() {
        return cards;
    }

    public void addACard(Card card){
        cards.add(card);
    }

    public int getCardsPoint() {
        int point=0;
        for(Card card:cards){
            point+=card.getPoint();

        }
        return point;
    }

    public int getScore() {
        return score;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setTempScore(int tempScore) {
        this.tempScore = tempScore;
    }

    public int getTempScore() {
        return tempScore;
    }

    public void addTempScore(int tempScore) {
        this.tempScore += tempScore;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public void setDeuce(int deuce) {
        this.deuce = deuce;
    }
}