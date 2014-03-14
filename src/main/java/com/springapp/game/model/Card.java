package com.springapp.game.model;

import com.google.common.base.Objects;

/**
 * Created by xya on 2/15/14.
 */
public class Card {
    public Card(FLOWER flower, POINT point) {
        this.point = point;
        this.flower = flower;
    }

    public enum FLOWER {SPADE, CLUB, HEART, DIAMOND};

    public enum POINT {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);
        private int point;

        private POINT(int point) {
            this.point = point;
        }
    }

    private POINT point;
    private FLOWER flower;

    public FLOWER getFlower() {
        return flower;
    }

    public int getPoint() {
        return point.point;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(point, flower);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return Objects.equal(this.point, other.point) && Objects.equal(this.flower, other.flower);
    }
}
