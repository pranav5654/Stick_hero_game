package com.example.ap_final;

public class Score {

    private int score = 0;
    static private int cherries  = 0 ;
    private int highscore = 0 ;

    private int last = 0  ;

    private Score(){};

    private static Score s;

    public static Score getScoreInstance(){
        if(s==null) {s =  new Score();}
        return s;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }
}
