package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public interface ScoringStrategy {
    /**
     * Updates the score after a ball has been pocketed
     * @param table
     */
    public void updateScore(PoolTable table);

}
