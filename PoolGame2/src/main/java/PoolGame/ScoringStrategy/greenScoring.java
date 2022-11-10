package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public class greenScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score += 3;
        table.setScore(score);
    }
}

