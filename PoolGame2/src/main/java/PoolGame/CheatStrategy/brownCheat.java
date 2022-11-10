package PoolGame.CheatStrategy;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import javafx.scene.paint.Color;

public class brownCheat implements CheatStrategy{

    public void removeBalls(PoolTable table){
        int tempScore = table.getScore();
        for (Ball ball : table.getBalls()){
            if (ball.getColour() == Color.BROWN){
                ball.disable();
                tempScore += 4;
            }
        }
        table.setScore(tempScore);
    }
}
