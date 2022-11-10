package PoolGame.Strategy;

import PoolGame.Items.PoolTable;

public class ScoringContext {

    private ScoringStrategy strategy;
    private int score;

    public void setScoringStrategy(ScoringStrategy strategy){
        this.strategy = strategy;
    }
    public void executeScoringStrategy(PoolTable table){
        strategy.updateScore(table);
    }
    public int getScore(){
        return this.score;
    }
}
