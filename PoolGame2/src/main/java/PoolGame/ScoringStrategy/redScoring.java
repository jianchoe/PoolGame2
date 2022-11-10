package PoolGame.ScoringStrategy;

import PoolGame.Items.PoolTable;

public class redScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score++;
        table.setScore(score);
    }
}
