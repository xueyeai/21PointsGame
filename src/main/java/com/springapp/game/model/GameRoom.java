package com.springapp.game.model;

import com.springapp.game.exception.GameNotStartException;
import com.springapp.game.exception.RoomIsFullException;
import com.springapp.game.exception.RoomIsNotPreparedException;
import com.springapp.game.exception.RoomStateException;

import java.util.*;

/**
 * Created by xya on 2/17/14.
 */
public class GameRoom {
    public static final int CAPACITY = 2;
    private int capacity = 2;
    private int roomNo = 1;
    private int roomState = 0;
    private List<Player> playerList = new ArrayList<>();
    private List<Player> nextPlayerList = new ArrayList<>();
    private Cards cards = new Cards();

    public enum RoomState {WAITTING, PREPARING, PREPARED, PLAYING}

    public GameRoom(int roomNo, Cards cards, int capacity) {
        this.cards = cards;
        this.capacity = capacity;
        this.roomNo = roomNo;
    }

    public GameRoom(int roomNo, Cards cards) {
        this(roomNo, cards, CAPACITY);
    }

    public GameRoom(int roomNo) {
        this(roomNo, new Cards(), CAPACITY);
    }

    public GameRoom(int roomNo, int capacity) {
        this(roomNo, new Cards(), capacity);
    }

    public void joinRoom(Player player) throws Exception {
        if (playerList.size() < capacity) {
            playerList.add(player);
            player.setRoomNo(roomNo);
        } else {
            throw new RoomIsFullException("Room Is Full!");
        }
    }

    public void prepared(Player player) throws Exception {
        if (getRoomState() == RoomState.PREPARING) {
            changeRoomState(player);
        } else {
            throw new RoomStateException("Room Is Not In An Legal State!");
        }

    }

    private void changeRoomState(Player player) throws Exception {
        int x = 1 << playerList.indexOf(player);
        roomState += x;
        if (roomState > (1 << capacity)) {
            throw new RoomStateException("Room State Code Is Out Of Range!");
        }
    }

    private void changeRoomState(RoomState state) {
        if (state == RoomState.PLAYING) {
            roomState = 1 << capacity;
        }
        if (state == RoomState.WAITTING) {
            roomState = 0;
        }
    }

    public void startGame() throws Exception {
        if (getRoomState() != RoomState.PREPARED) {
            throw new RoomIsNotPreparedException("Room Is Not Prepared!");
        }
        changeRoomState(RoomState.PLAYING);
        dealCardsAtTheBeginning();
    }

    public GameResult endGame(Player player) throws Exception {
        GameResult gameResult = endGame();
        gameResult.addLoser(player);
        return gameResult;
    }

    public GameResult endGame() throws Exception {
        sortPlayerList();
        nextPlayerList.clear();
        nextPlayerList.addAll(playerList);

        Map<String, Integer> pointMap = getLoserAndWonnerPointMap();

        cards.shuffle();
        playerList.clear();
        playerList.addAll(nextPlayerList);
        changeRoomState(RoomState.WAITTING);
        return getGameResult(pointMap.get("loserPoint"), pointMap.get("wonnerPoint"));
    }

    private Map<String, Integer> getLoserAndWonnerPointMap() {
        Map<String, Integer> pointMap = new HashMap<>();

        int theFirstBigThan21Index = -1;
        for (Player player : playerList) {
            if (player.getCardsPoint() > 21) {
                theFirstBigThan21Index = playerList.indexOf(player);
                break;
            }
        }
        if (theFirstBigThan21Index > 0) {
            pointMap.put("loserPoint", playerList.get(playerList.size() - 1).getCardsPoint());
            pointMap.put("wonnerPoint", playerList.get(theFirstBigThan21Index - 1).getCardsPoint());
        } else if (theFirstBigThan21Index == 0) {
            pointMap.put("loserPoint", playerList.get(playerList.size() - 1).getCardsPoint());
            pointMap.put("wonnerPoint", playerList.get(0).getCardsPoint());
        } else if (theFirstBigThan21Index == -1) {
            pointMap.put("loserPoint", playerList.get(0).getCardsPoint());
            pointMap.put("wonnerPoint", playerList.get(playerList.size() - 1).getCardsPoint());
        }
        return pointMap;
    }

    private GameResult getGameResult(int loserPoint, int wonnerPoint) {
        GameResult gameResult = new GameResult();
        if (loserPoint == wonnerPoint) {
            gameResult.setDeduce(playerList);
        } else {
            getGameResultIfSomeoneWin(loserPoint, wonnerPoint, gameResult);
        }
        return gameResult;
    }

    private void getGameResultIfSomeoneWin(int loserPoint, int wonnerPoint, GameResult gameResult) {
        for (Player player : playerList) {
            if (player.getCardsPoint() == wonnerPoint) {
                nextPlayerList.remove(player);
                nextPlayerList.set(0, player);
                gameResult.addWonner(player);
            } else if (player.getCardsPoint() == loserPoint) {
                gameResult.addLoser(player);
            } else {
                gameResult.addDeuce(player);
            }
        }
    }

    private void sortPlayerList() {
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getCardsPoint() > o2.getCardsPoint()) {
                    return 1;
                } else if (o1.getCardsPoint() < o2.getCardsPoint()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public RoomState getRoomState() {
        if (playerList.size() < capacity) {
            return RoomState.WAITTING;
        } else if (roomState == (1 << capacity)) {
            return RoomState.PLAYING;
        } else if (roomState == ((1 << capacity) - 1)) {
            return RoomState.PREPARED;
        }
        return RoomState.PREPARING;
    }

    public void leftRoom(Player player) throws Exception {
        player.initTempVariable();
        if (getRoomState() == RoomState.PLAYING) {
            playerList.remove(player);
            endGame(player);
        } else {
            playerList.remove(player);
        }
    }

    private void dealCardsAtTheBeginning() throws Exception {
        for (int i = 0; i < 2; i++) {
            for (Player player : playerList) {
                dealCardsTo(player);
            }
        }
    }

    public int dealCardsTo(Player player) throws Exception {
        if (getRoomState() == RoomState.PLAYING) {
            player.addACard(cards.deal());
            return player.getCardsPoint();
        } else {
            throw new GameNotStartException("Game Is Not Start So You Can Not Deal Cards!!");
        }
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getPlayerNum() {
        return playerList.size();
    }

}
