package PoolGame.PocketBuilder;

import PoolGame.Items.Pocket;
public interface PocketBuilder {
    /**
     * Creates a new pocket to be built
     */
    public void reset();
    public void setxPos(double xPos);
    public void setyPos(double yPos);
    public void setRadius(double radius);

    /**
     * Constructs the pocket
     * @return
     */
    public Pocket build();
}
