package com.springapp.game.model;

import com.springapp.game.exception.OutOfCapacityException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by xya on 2/16/14.
 */
public class CardsTest {
    private Cards cards;

    @Before
    public void setUp() throws Exception {
        cards = new Cards();
    }

    @Test
    public void should_deal_will_return_a_card() throws Exception {
        assertThat(cards.deal(), is(Card.class));
    }

    @Test
    public void should_deal_will_return_a_random_card() throws Exception {
        Set<Card> dealedCardSet = new HashSet();
        for (int i = 0; i < 20; i++) {
            cards = new Cards();
            dealedCardSet.add(cards.deal());
        }
        assertThat(dealedCardSet.size() > 1, is(true));
    }

    @Test
    public void should_deal_returned_card_is_in_used_card_list() throws Exception {
        Card dealedCard = cards.deal();
        assertThat(cards.getUsed().contains(dealedCard), is(true));
    }

    @Test
    public void should_deal_returned_card_is_not_in_remain_card_list() throws Exception {
        Card dealedCard = cards.deal();
        assertThat(cards.getRemain().contains(dealedCard), is(false));
    }

    @Test
    public void should_deal_will_deal_all_cards() throws Exception {
        runDeal52Times();
        assertThat(cards.getRemain().size() == 0, is(true));
    }

    @Test(expected = OutOfCapacityException.class)
    public void should_Exception_of_OutOfCapacity_works() throws Exception {
        runDeal52Times();
        cards.deal();
    }

    private void runDeal52Times() throws OutOfCapacityException {
        for (int i = 0; i < cards.getInit().size(); i++) {
            cards.deal();
        }
    }

}
