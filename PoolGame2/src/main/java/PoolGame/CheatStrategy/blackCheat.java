package PoolGame.CheatStrategy;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import javafx.scene.paint.Color;

public class blackCheat implements CheatStrategy{

    public void removeBalls(PoolTable table){
        int tempScore = table.getScore();
        for (Ball ball : table.getBalls()){
            if (ball.getColour() == Color.BLACK){
                ball.disable();
                tempScore += 7;
            }
        }
        table.setScore(tempScore);
    }
}
