package com.springapp.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xya on 2/15/14.
 */
public class Player {
    private List<Card> cards = new ArrayList<>();
    private int id = 0;
    private int score = 0;
    private int win = 0;
    private int lose = 0;
    private int deuce = 0;
    private String username = "";
    private int tempScore = 0;
    private int roomNo = 0;

    public Player() {
    }

    public Player(int id, String username, int score, int win, int lose, int deuce) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.win = win;
        this.lose = lose;
        this.deuce = deuce;
        initTempVariable();
    }

    public void initTempVariable() {
        setRoomNo(0);
        setTempScore(0);
    }

    public void addACard(Card card) {
        cards.add(card);
    }

    public int getCardsPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getPoint();

        }
        return point;
    }

    public void addTempScore(int tempScore) {
        this.tempScore += tempScore;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDeuce() {
        return deuce;
    }

    public void setDeuce(int deuce) {
        this.deuce = deuce;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTempScore() {
        return tempScore;
    }

    public void setTempScore(int tempScore) {
        this.tempScore = tempScore;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
}
