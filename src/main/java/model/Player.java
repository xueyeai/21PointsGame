package model;

import java.util.List;

/**
 * Created by xya on 2/15/14.
 */
public class Player {
    private List<Card> cards;
    private User user;

    public List<Card> getCards() {
        return cards;
    }

    public void addACard(Card card){
        cards.add(card);
    }

    public void subACard(Card card){
        cards.remove(card);
    }

}
