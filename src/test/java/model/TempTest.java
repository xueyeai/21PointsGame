package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by xya on 2/19/14.
 */
public class TempTest {
    @Test

    public void tempTest(){
        List<Integer> i=new ArrayList<>();
        i.add(1);
        i.add(2);
        i.add(5);
        i.add(0);
        i.add(4);
        List<Integer> b=new ArrayList<>();
        b=i;
        b.add(7);
        System.out.printf(String.valueOf(b));
        System.out.printf(String.valueOf(i));
        assertThat(1, is(1));
    }


}
