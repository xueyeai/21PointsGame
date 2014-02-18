package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xya on 2/15/14.
 */
public class Player {
    private List<Card> cards=new ArrayList<>();
    private String userName;
    private int userId;

    public List<Card> getCards() {
        return cards;
    }

    public Player(User user){
        this.userName=user.getUserName();
        this.userId=user.getId();
    }

    public Player(){
        this.userName="Anonymous";
        this.userId=0;
    }

    public void addACard(Card card){
        cards.add(card);
    }

    public void subACard(Card card){
        cards.remove(card);
    }

    public int calculatePoints(){
        int point=0;
        for(Card card: cards){
            point+=card.getPoint();
        }
        return point;
    }

}
