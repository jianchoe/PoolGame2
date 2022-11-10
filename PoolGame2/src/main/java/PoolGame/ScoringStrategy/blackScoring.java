package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public class blackScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score += 7;
        table.setScore(score);
    }
}
