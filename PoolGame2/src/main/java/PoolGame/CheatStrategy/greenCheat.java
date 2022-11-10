package PoolGame.CheatStrategy;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import javafx.scene.paint.Color;

public class greenCheat implements CheatStrategy{

    public void removeBalls(PoolTable table){
        int tempScore = table.getScore();
        for (Ball ball : table.getBalls()){
            if (ball.getColour() == Color.SEAGREEN){
                ball.disable();
                tempScore += 3;
            }
        }
        table.setScore(tempScore);
    }
}
