package model;

import java.util.List;

/**
 * Created by xya on 2/15/14.
 */
public class Cards {
    private List<Card> exclude;
    private List<Card> remain;
    private List<Card> used;

    public List<Card> getExclude() {
        return exclude;
    }

    public void setExclude(List<Card> exclude) {
        this.exclude = exclude;
    }

    public List<Card> getRemain() {
        return remain;
    }

    public void setRemain(List<Card> remain) {
        this.remain = remain;
    }

    public List<Card> getUsed() {
        return used;
    }

    public void setUsed(List<Card> used) {
        this.used = used;
    }
}
