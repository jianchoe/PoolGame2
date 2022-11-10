package PoolGame.Momento;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;

import java.util.List;

public class Originator {

    private int second;
    private int minute;
    private int score;
    private List<Ball> balls;

    /**
     * @param table
     * @return the saved snapshot of the current game state
     */
    public Memento saveState(PoolTable table){
        return new Memento(table);
    }

    /**
     * Retrieves the previously saved snapshot of the game state
     * @param memento
     */
    public void recoverState(Memento memento){
        this.second = memento.getSecond();
        this.minute = memento.getMinute();
        this.score = memento.getScore();
        this.balls = memento.getBalls();
    }
}
