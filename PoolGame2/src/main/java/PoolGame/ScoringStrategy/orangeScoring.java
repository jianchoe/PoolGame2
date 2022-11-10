package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public class orangeScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score += 8;
        table.setScore(score);
    }
}
