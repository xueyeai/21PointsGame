package com.springapp.game.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xya on 2/19/14.
 */
public class GameResult {
    private Set<Player> losers = new HashSet<>();
    private Set<Player> wonners = new HashSet<>();
    private Set<Player> deuces = new HashSet<>();

    public void addLoser(Player player) {
        this.losers.add(player);
    }

    public void addWonner(Player player) {
        this.wonners.add(player);
    }

    public void setDeduce(List<Player> playerList) {
        this.deuces.addAll(playerList);
    }

    public void addDeuce(Player player) {
        this.deuces.add(player);
    }

    public Set<Player> getLosers() {
        return losers;
    }

    public Set<Player> getWonners() {
        return wonners;
    }

    public Set<Player> getDeuces() {
        return deuces;
    }
}
