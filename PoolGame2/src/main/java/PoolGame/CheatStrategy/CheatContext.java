package PoolGame.CheatStrategy;

import PoolGame.Items.PoolTable;
import PoolGame.ScoringStrategy.ScoringStrategy;

public class CheatContext {
    private CheatStrategy strategy;

    public void setCheatStrategy(CheatStrategy strategy){
        this.strategy = strategy;
    }
    public void executeCheatStrategy(PoolTable table){
        strategy.removeBalls(table);
    }
}
