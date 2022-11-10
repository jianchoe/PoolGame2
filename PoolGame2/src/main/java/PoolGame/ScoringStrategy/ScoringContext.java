package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public class ScoringContext {

    private ScoringStrategy strategy;
    private int score;

    /**
     * Sets the scoring strategy
     * @param strategy
     */
    public void setScoringStrategy(ScoringStrategy strategy){
        this.strategy = strategy;
    }

    /**
     * Executes the algorithm for scoring for respective balls
     * @param table
     */
    public void executeScoringStrategy(PoolTable table){
        strategy.updateScore(table);
    }
    public int getScore(){
        return this.score;
    }
}
