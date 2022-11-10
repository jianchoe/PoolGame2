package PoolGame.Strategy;

import PoolGame.Items.PoolTable;
import javafx.scene.paint.Color;

public class redScoring implements ScoringStrategy {

    public void updateScore(PoolTable table){
        int score = table.getScore();
        score++;
        table.setScore(score);
    }
}
