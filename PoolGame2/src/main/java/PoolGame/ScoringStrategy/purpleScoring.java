package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public class purpleScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score += 6;
        table.setScore(score);
    }
}
