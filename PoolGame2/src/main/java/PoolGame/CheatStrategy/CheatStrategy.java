package PoolGame.CheatStrategy;

import PoolGame.Items.PoolTable;

public interface CheatStrategy {

    /**
     * removes the appropriate ball and adds points
     * equal to the score
     * @param table
     */
    public void removeBalls(PoolTable table);
}
