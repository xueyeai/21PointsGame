package model;

/**
 * Created by xya on 2/15/14.
 */
public class Card {
    public Card(FLOWER flower, POINT point) {
        this.point=point;
        this.flower=flower;
    }

    public enum FLOWER {SPADE,CLUB,HEART,DIAMOND};
    public enum POINT {
        ACE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),JACK(10),QUEEN(10),KING(10);
        private int point;
        private POINT(int point){
            this.point=point;
        }
    }
    private POINT point;
    private FLOWER flower;

    public FLOWER getFlower() {return flower; }

    public POINT getPoint() {
        return point;
    }


}
