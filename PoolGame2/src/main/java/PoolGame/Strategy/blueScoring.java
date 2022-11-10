package PoolGame.Strategy;

import PoolGame.Items.PoolTable;

public class blueScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score += 5;
        table.setScore(score);
    }
}
