package model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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

    @Test
    public void should_deal_will_deal_all_cards() throws Exception{
        cards=new Cards();
        for(int i=0;i<cards.getInit().size();i++){
            cards.deal();
        }
        assertThat(cards.getRemain().size()==0,is(true));
    }

    @Test
    public void should_Exception_of_OutOfCapacity_works() throws Exception{
        cards=new Cards();
        try{
            for(int i=0;i<cards.getInit().size();i++){
                cards.deal();
            }
            cards.deal();
        }catch (Exception e){
            if(e instanceof OutOfCapacityException){
                return;
            }
            fail();
        }
    }

}
