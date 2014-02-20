package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xya on 2/15/14.
 */
public class Cards {
    private List<Card> init=new ArrayList<>();
    private List<Card> remain=new ArrayList<>();
    private List<Card> used=new ArrayList<>();

    public Cards(){
        for(Card.FLOWER flower: Card.FLOWER.values()){
            for(Card.POINT point: Card.POINT.values()){
                init.add(new Card(flower, point));
            }
        }

        remain.addAll(init);
    }

    public Card deal() throws OutOfCapacityException{
        if (remain.size()==0){
            throw new OutOfCapacityException("Out Of Remain Cards Capacity!");
        }
        Card card=remain.get((int)(Math.random()*100%remain.size()));
        remain.remove(card);
        used.add(card);
        return card;
    }

    public List<Card> getInit() {
        return init;
    }

    public List<Card> getRemain() {
        return remain;
    }

    public List<Card> getUsed() {
        return used;
    }


    public void shuffle() {
        remain.clear();
        used.clear();
        remain.addAll(init);

    }
}

