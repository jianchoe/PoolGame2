package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public class brownScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score += 4;
        table.setScore(score);
    }
}
