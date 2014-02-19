package model;

/**
 * Created by xya on 2/16/14.
 */
public class User {
    private String userName;
    private int id;
    private String passwd;
    private int roomNum;
    private int score;

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int addScore) {
        this.score += addScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
