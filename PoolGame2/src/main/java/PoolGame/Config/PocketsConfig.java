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
        JSONArray json = (JSONArray) obj;

        for(Object p : json){
            list.add(new PocketConfig(p));
        }
        this.init(list);
        return this;
    }
    public List<PocketConfig> getPocketConfig(){
        return this.pocket;
    }

}
