package PoolGame.Momento;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;

import java.util.*;

public class Memento {

    private int second;
    private int minute;
    private int score;
    private List<Ball> balls;

    public Memento(PoolTable table){
        this.second = table.getSecond();
        this.minute = table.getMinute();
        this.score = table.getScore();
        this.balls = table.getBalls();
    }
    public int getSecond(){
        return this.second;
    }
    public int getMinute(){
        return this.minute;
    }
    public int getScore(){
        return this.score;
    }
    public List<Ball> getBalls(){
        return this.balls;
    }
}

