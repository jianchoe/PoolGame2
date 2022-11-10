package PoolGame.Momento;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;

import java.util.List;

public class Originator {

    private int second;
    private int minute;
    private int score;
    private List<Ball> balls;

    public Memento saveState(PoolTable table){
        return new Memento(table);
    }
    public void recoverState(Memento memento){
        this.second = memento.getSecond();
        this.minute = memento.getMinute();
        this.score = memento.getScore();
        this.balls = memento.getBalls();
    }
}
