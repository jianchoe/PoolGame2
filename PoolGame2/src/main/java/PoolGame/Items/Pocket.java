package PoolGame.Items;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** The pocket of a pool table */
public class Pocket implements Drawable {
    
    /** The radius of the pocket */
    public static final double RADIUS = Ball.RADIUS + 5;
    /** The colour of the pocket */
    protected Color colour = Color.BLACK;
    /** The JavaFX shape of the pocket */
    protected Circle shape;
    private double posX;
    private double posY;
    private double radius;
    private boolean flag;

    /**
     * Initialise the pool table pocket with the provided value
     * @param posX The x coordinate position of the pocket
     * @param posY The y coordinate position of the pocket
     */
    public Pocket(double posX, double posY) {
        this.shape = new Circle(posX, posY, Pocket.RADIUS, this.colour);
    }

    public Pocket(double posX, double posY, double radius){
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
    }
    public Pocket(){

    }

    /**
     * Check if a point is in the pocket bounds
     * @param point A point to check.
     * @return True if the point is in the pocket bounds, false otherwise
     */
    public boolean isInPocket(Point2D point) {
        return this.shape.getBoundsInLocal().contains(point);
    }

    public Node getNode() {
        return this.shape;
    }

    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.shape);
    }

    public void addToGroup(ObservableList<Node> groupChildren, boolean flag) {
        if (flag == false){
            this.shape = new Circle(this.posX, this.posY, this.radius, this.colour);
        }
        groupChildren.add(this.shape);
    }

    public void removeFromGroup(ObservableList<Node> groupChildren){
        groupChildren.remove(this.shape);
    }

    public void setxPos(double posX){
        this.posX = posX;
    }
    public void setyPos(double posY){
        this.posY = posY;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
}
