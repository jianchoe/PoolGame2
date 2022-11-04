package PoolGame.Factory;

import PoolGame.Config.BallsConfig;
import PoolGame.Config.Configurable;
import PoolGame.Config.PocketsConfig;
import org.json.simple.JSONObject;

public class PocketsConfigFactory implements ConfigFactory {
    public Configurable make(Object jSONObject) {
        Object obj = ((JSONObject)jSONObject).get("pockets");
        return new PocketsConfig(obj);
    }
}
