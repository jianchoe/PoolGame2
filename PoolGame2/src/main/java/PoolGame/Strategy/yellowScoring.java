package PoolGame.Strategy;

import PoolGame.Items.PoolTable;

public class yellowScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score += 2;
        table.setScore(score);
    }
}
