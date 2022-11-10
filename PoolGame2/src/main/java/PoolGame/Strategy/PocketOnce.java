package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Strategy.BallPocketStrategy;

/** Hide the ball once it falls into the pocket */
public class PocketOnce implements BallPocketStrategy {
    public void fallIntoPocket(Game game, Ball ball) {
        ball.disable();
        game.getPoolTable().score(ball);
    }
}
