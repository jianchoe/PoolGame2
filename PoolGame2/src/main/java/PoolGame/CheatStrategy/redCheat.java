package PoolGame.CheatStrategy;

import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import javafx.scene.paint.Color;

public class redCheat implements CheatStrategy{

    public void removeBalls(PoolTable table){
        int tempScore = table.getScore();
        for (Ball ball : table.getBalls()){
            if (ball.getColour() == Color.RED){
                ball.disable();
                tempScore += 1;
            }
        }
        table.setScore(tempScore);
    }
}
