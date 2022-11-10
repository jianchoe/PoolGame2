package PoolGame.CheatStrategy;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import javafx.scene.paint.Color;

public class blueCheat implements CheatStrategy{

    public void removeBalls(PoolTable table){
        int tempScore = table.getScore();
        for (Ball ball : table.getBalls()){
            if (ball.getColour() == Color.BLUE){
                ball.disable();
                tempScore += 5;
            }
        }
        table.setScore(tempScore);
    }
}

