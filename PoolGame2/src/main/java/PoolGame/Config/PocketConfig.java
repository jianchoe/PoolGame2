package PoolGame.Config;

import org.json.simple.JSONObject;

import javax.swing.text.Position;

public class PocketConfig implements Configurable{

    private PositionConfig position;

    private double radius;

    public PocketConfig(Object obj){
        this.parseJSON(obj);
    }

    private void init(PositionConfig posConf, double radius){
        if(radius <= 0){
            throw new IllegalArgumentException("Raidus of pocket must be greater than 0");
        }
        this.position = posConf;
        this.radius = radius;
    }

    public Configurable parseJSON(Object obj){
        JSONObject json = (JSONObject) obj;
        PositionConfig posConf = new PositionConfig(json.get("position"));
        double radius = (double) json.get("radius");
        this.init(posConf, radius);
        return null;
    }

    public PositionConfig getPosConf(){
        return this.position;
    }
    public double getRadius(){
        return this.radius;
    }
}
