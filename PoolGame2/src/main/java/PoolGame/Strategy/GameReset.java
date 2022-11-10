package PoolGame.Strategy;

import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Strategy.BallPocketStrategy;

/** Resets game when the method of this instance is called */
public class GameReset implements BallPocketStrategy {
    public void fallIntoPocket(Game game, Ball ball) {
        game.getPoolTable().setScore(0);
        game.getPoolTable().resetTimer();
        game.reset();
    }
}
