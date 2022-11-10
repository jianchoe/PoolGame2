package PoolGame.Config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

import javax.swing.text.Position;

public class PocketConfig implements Configurable{

    private PositionConfig position;

    private double radius;

    /**
     * Initialise the instance with the provided JSONObject
     * @param obj A JSONObject containing the pocket configuration
     */
    public PocketConfig(Object obj){
        this.parseJSON(obj);
    }

    private void init(PositionConfig posConf, double radius){
        if(radius <= 0){
            throw new IllegalArgumentException("Radius of pocket must be greater than 0");
        }
        this.position = posConf;
        this.radius = radius;
    }

    public Configurable parseJSON(Object obj){
        List<PositionConfig> pockets = new ArrayList<>();
        List<Double> radiusList = new ArrayList<>();

        JSONObject jsonPocket = (JSONObject) obj;
        PositionConfig posConf = new PositionConfig(jsonPocket.get("position"));
        double radius = (double) jsonPocket.get("radius");

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
