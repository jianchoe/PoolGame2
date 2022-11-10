package PoolGame.PocketBuilder;

import PoolGame.Items.Pocket;
import PoolGame.PocketBuilder.PocketBuilder;

public class PocketBuilderGeneral implements PocketBuilder {

    private Pocket pocket;

    public PocketBuilderGeneral(){
        this.reset();
    }
    public void reset(){
        pocket = new Pocket();
    };
    public void setxPos(double xPos){
        pocket.setxPos(xPos);
    };
    public void setyPos(double yPos){
        pocket.setyPos(yPos);
    }
    public void setRadius(double radius){
        pocket.setRadius(radius);
    }
    public Pocket build(){
        Pocket pocket = this.pocket;
        this.reset();
        return pocket;
    }

}
