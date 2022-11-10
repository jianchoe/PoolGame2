package PoolGame.PocketBuilder;

import PoolGame.Items.Pocket;
public interface PocketBuilder {

    public void reset();
    public void setxPos(double xPos);
    public void setyPos(double yPos);
    public void setRadius(double radius);
    public Pocket build();
}
