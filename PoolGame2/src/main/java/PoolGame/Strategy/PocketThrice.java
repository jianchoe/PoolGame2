package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Strategy.BallPocketStrategy;

public class PocketThrice implements BallPocketStrategy {

    private final int FALL_COUNTER_THRESHOLD = 3;

    public void fallIntoPocket(Game game, Ball ball) {
        ball.incrementFallCounter();
        if (ball.getFallCounter() >= FALL_COUNTER_THRESHOLD) {
            ball.disable();
            game.getPoolTable().score(ball);
        } else {
            ball.resetPosition();
            for (Ball ballB: game.getPoolTable().getBalls()) {
                if (ball.isColliding(ballB)) {
                    ball.disable();
                    game.getPoolTable().score(ball);
                }
            }
        }
    }
}
