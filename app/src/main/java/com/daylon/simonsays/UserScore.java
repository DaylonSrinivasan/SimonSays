package com.daylon.simonsays;

/**
 * Created by Daylon on 2/27/2016.
 *
 * Firebase information
 * 
 */
public class UserScore {
    private String username;
    private int score;

    public UserScore() {}

    public UserScore(String username, int score){
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public String toString() {return username + ": " + score; }

}
