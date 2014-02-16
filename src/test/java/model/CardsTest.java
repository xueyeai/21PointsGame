package model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by xya on 2/16/14.
 */
public class CardsTest{
    private Cards cards;

    @Test
    public void should_deal_will_return_a_card()throws Exception{
        cards=new Cards();
        assertThat(cards.deal(),is(Card.class));
    }

    @Test
    public void should_deal_will_return_a_random_card() throws Exception{
        Set<Card> dealedCardSet=new HashSet();
        for(int i=0;i<20;i++){
            cards=new Cards();
            dealedCardSet.add(cards.deal());
        }
        assertThat(dealedCardSet.size()>1,is(true));
    }

    @Test
    public void should_deal_returned_card_is_in_used_card_list() throws Exception{
        cards=new Cards();
        Card dealedCard=cards.deal();
        assertThat(cards.getUsed().contains(dealedCard),is(true));
    }

    @Test
    public void should_deal_returned_card_is_not_in_remain_card_list() throws Exception{
        cards=new Cards();
        Card dealedCard=cards.deal();
        assertThat(cards.getRemain().contains(dealedCard),is(false));
    }


}
