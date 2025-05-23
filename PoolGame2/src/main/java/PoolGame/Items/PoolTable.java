package PoolGame.Items;

import java.util.*;

import PoolGame.Drawable;
import PoolGame.Game;
import PoolGame.Config.TableConfig;
import PoolGame.Items.Ball.BallType;
import PoolGame.ScoringStrategy.*;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** A pool table */
public class PoolTable implements Drawable {
    private long[] dim;
    private String colourName;
    private Color colour;
    private double friction;
    private Rectangle shape;
    private List<Ball> balls;
    private List<Pocket> pockets;
    private boolean flag;
    private int score = 0;
    private Map<String, ScoringStrategy> scoreRegister;
    private int second = 0;
    private int minute = 0;

    /**
     * Offset of pockets on the table.
     */
    public static final double POCKET_OFFSET = 5;

    /**
     * Build the pool table with the provided values
     * @param colourName The colour of the table in String
     * @param friction The friction of the table
     * @param dimX The dimension of the table in the x-axis
     * @param dimY The dimension of the table in the y-axis
     */
    public PoolTable(String colourName, double friction, long dimX, long dimY) {
        this.init(colourName, friction, dimX, dimY);
    }

    /**
     * Build the pool table using a `TableConfig` instance
     * @param config The `TableConfig` instance
     */
    public PoolTable(TableConfig config, boolean flag) {
        this.flag = flag;
        this.init(config.getColour(),
            config.getFriction(),
            config.getSizeConfig().getX(),
            config.getSizeConfig().getY());
    }

    private void init(String colourName, double friction, long dimX, long dimY) {
        this.colourName = colourName;
        this.colour = Color.valueOf(this.colourName);
        this.friction = friction;
        this.dim = new long[2];
        this.dim[0] = dimX;
        this.dim[1] = dimY;
        this.shape = new Rectangle(this.dim[0], this.dim[1], this.colour);
        this.balls = new LinkedList<>();
        this.pockets = new ArrayList<>();
        scoreRegister = new HashMap<>();
        this.scoringSetup();

        if (this.flag == true){
            this.pockets = new ArrayList<>();
            this.pockets.add(new Pocket(POCKET_OFFSET, POCKET_OFFSET));
            this.pockets.add(new Pocket(dimX / 2, POCKET_OFFSET));
            this.pockets.add(new Pocket(dimX - POCKET_OFFSET, POCKET_OFFSET));
            this.pockets.add(new Pocket(POCKET_OFFSET, dimY - POCKET_OFFSET));
            this.pockets.add(new Pocket(dimX / 2, dimY - POCKET_OFFSET));
            this.pockets.add(new Pocket(dimX - POCKET_OFFSET, dimY - POCKET_OFFSET));
        }
    }

    /**
     * Get the x dimension of the table.
     * @return The dimension of the table for the x axis.
     */
    public long getDimX() {
        return this.dim[0];
    }

    /**
     * Get the y dimension of the table.
     * @return The dimension of the table for the y axis.
     */
    public long getDimY() {
        return this.dim[1];
    }

    /**
     * Get the colour of the table.
     * @return The colour of the table.
     */
    public Color getColour() {
        return this.colour;
    }

    /**
     * Get the friction of the table.
     * @return The friction value of the table.
     */
    public double getFriction() {
        return this.friction;
    }

    public Node getNode() {
        return this.shape;
    }

    /**
     * Add a ball onto the pool table
     * @param ball The ball to be added
     */
    public void addBall(Ball ball) {
        if (!this.balls.contains(ball)) {
            this.balls.add(ball);
        }
    }

    /**
     * Get all balls on table.
     * @return List of ball on the table.
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * Set up table with the list of balls, which includes the CueBall.
     * @param balls The list of balls to be added to the table
     */
    public void setupBalls(List<Ball> balls) {
        for (Ball ball : balls) {
            // if (ball.getBallType() == Ball.BallType.CUEBALL) {
            //     this.setCueBall(ball);
            // }
            this.addBall(ball);
        }
    }

    /**
     * Set up table with the list of pockets.
     * @param pockets The list of balls to be added to the table
     */
    public void setupPockets(List<Pocket> pockets){
        for (Pocket pocket : pockets){
            if(!this.pockets.contains(pocket)){
                this.pockets.add(pocket);
            }
        }
    }

    /**
     * Add the table, the balls and pockets to the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    @Override
    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.shape);
        for (Pocket pocket : this.pockets) {
            if (flag == true){
                pocket.addToGroup(groupChildren, true);
            }
            else {
                pocket.addToGroup(groupChildren, false);
            }
        }
        for (Ball ball : this.balls) {
            ball.addToGroup(groupChildren);
        }
    }
    /**
     * Remove the table, the balls and pockets from the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    public void removeFromGroup(ObservableList<Node> groupChildren) {
        groupChildren.remove(this.shape);
        for (Pocket pocket : this.pockets) {
            pocket.removeFromGroup(groupChildren);
        }
        for (Ball ball : this.balls) {
            ball.removeFromGroup(groupChildren);
        }
    }

    /**
     * Apply friction to all the balls
     */
    public void applyFrictionToBalls() {
        for (Ball ball : this.balls) {
            ball.applyFriction(this.getFriction());
        }
    }

    /**
     * Check if any of the balls is in a pocket and handle the ball in the 
     * pocket
     * @param game The instance of the game
     */
    public void checkPocket(Game game) {
        for (Pocket pocket : this.pockets) {
            for (Ball ball : this.balls) {
                if (ball.isDisabled()) {
                    continue;
                }
                Point2D ballCenter = new Point2D(ball.getXPos(), ball.getYPos());
                if (pocket.isInPocket(ballCenter)) {
                    ball.fallIntoPocket(game);
                }
            }
        }
    }

    /**
     * Performs the respective scoring strategies of a ball
     * @param ball pocketed ball
     */
    public void score(Ball ball){
        ScoringContext context = new ScoringContext();

        if (ball.getColour() == Color.RED){
            String key = "red";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
        if (ball.getColour() == Color.GOLD){
            String key = "yellow";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
        if (ball.getColour() == Color.SEAGREEN){
            String key = "green";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
        if (ball.getColour() == Color.BROWN){
            String key = "brown";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
        if (ball.getColour() == Color.BLUE){
            String key = "blue";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
        if (ball.getColour() == Color.PURPLE){
            String key = "purple";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
        if (ball.getColour() == Color.BLACK){
            String key = "black";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
        if (ball.getColour() == Color.ORANGE){
            String key = "orange";
            context.setScoringStrategy(scoreRegister.get(key));
            context.executeScoringStrategy(this);
        }
    }

    /**
     * Puts the respective scoring strategies for different coloured balls
     * into a register
     */
    public void scoringSetup(){
        scoreRegister.put("red", new redScoring());
        scoreRegister.put("yellow", new yellowScoring());
        scoreRegister.put("green", new greenScoring());
        scoreRegister.put("brown", new brownScoring());
        scoreRegister.put("blue", new blueScoring());
        scoreRegister.put("purple", new purpleScoring());
        scoreRegister.put("black", new blackScoring());
        scoreRegister.put("orange", new orangeScoring());
    }

    /**
     * Handle the collision between the balls and table and between balls.
     */
    public void handleCollision() {
        Bounds tableBounds = this.shape.getBoundsInLocal();
        for (Ball ball : this.balls) {
            if (ball.isDisabled()) {
                continue;
            }
            Bounds ballBound = ball.getLocalBounds();
            if (!tableBounds.contains(ballBound)) {
                if (ballBound.getMaxX() >= tableBounds.getMaxX()) {
                    ball.setXVel(-ball.getXVel());
                    ball.setXPos(tableBounds.getMaxX() - ball.getRadius());
                } else if (ballBound.getMinX() <= tableBounds.getMinX()){
                    ball.setXVel(-ball.getXVel());
                    ball.setXPos(tableBounds.getMinX() + ball.getRadius());
                }
                if (ballBound.getMaxY() >= tableBounds.getMaxY()) {
                    ball.setYVel(-ball.getYVel());
                    ball.setYPos(tableBounds.getMaxY() - ball.getRadius());
                } else if (ballBound.getMinY() <= tableBounds.getMinY()) {
                    ball.setYVel(-ball.getYVel());
                    ball.setYPos(tableBounds.getMinY() + ball.getRadius());
                }
            }
            for (Ball ballB : this.balls) {
                if (ballB.isDisabled()) {
                    continue;
                }
                // if (ball.getBallType() == BallType.CUEBALL && ball.isColliding(ballB)) {
                //     System.out.printf("%f, %f, %s\n", ballB.getXVel(), ballB.getYVel(), ballB.isColliding(ball));
                // }
                if (ball.isColliding(ballB)) {
                    ball.handleCollision(ballB);
                }
            }
            
        }
    }

    /**
     * If all the balls has been disabled except the cue ball, the game has ended
     * and the player won.
     * @return The win status of the game.
     */
    public boolean hasWon() {
        boolean won = true;
        for (Ball ball : this.balls) {
            if (ball.getBallType() == BallType.CUEBALL) {
                continue;
            }
            if (!ball.isDisabled()) {
                won = false;
                break;
            }
        }
        return won;
    }

    /**
     * Reset the game.
     */
    public void reset() {
        for (Ball ball : this.balls) {
            ball.reset();
        }
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int num){
        this.score = num;
    }

    public void setSecond(int second){
        this.second = second;
    }
    public void setMinute(int minute){
        this.minute = minute;
    }
    public int getSecond(){
        return this.second;
    }
    public int getMinute(){
        return this.minute;
    }
    public void resetTimer(){
        this.setSecond(0);
        this.setMinute(0);
    }

}
