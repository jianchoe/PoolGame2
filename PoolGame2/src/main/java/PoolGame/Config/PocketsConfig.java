package PoolGame.Config;

import java.util.*;
import org.json.simple.JSONArray;

public class PocketsConfig implements Configurable{

    private List<PocketConfig> pocket;

    public PocketsConfig(Object obj){
        this.parseJSON(obj);
    }

    public void init(List<PocketConfig> pocketList){
        this.pocket = pocketList;
    }
    public Configurable parseJSON(Object obj){
        List<PocketConfig> list = new ArrayList<>();
        JSONArray jsonPockets = (JSONArray) obj;

        for(Object pocket : jsonPockets){
            list.add(new PocketConfig(pocket));
        }
        this.init(list);
        return this;
    }
    public List<PocketConfig> getPocketConfigs(){
        return this.pocket;
    }

}
