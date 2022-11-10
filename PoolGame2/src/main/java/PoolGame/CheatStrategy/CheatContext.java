package PoolGame.CheatStrategy;

import PoolGame.Items.PoolTable;
import PoolGame.ScoringStrategy.ScoringStrategy;

public class CheatContext {
    private CheatStrategy strategy;

    /**
     * Sets the strategy for cheating
     * @param strategy
     */
    public void setCheatStrategy(CheatStrategy strategy){
        this.strategy = strategy;
    }

    /**
     * Executes the strategy for corresponding ball
     * @param table
     */
    public void executeCheatStrategy(PoolTable table){
        strategy.removeBalls(table);
    }
}
