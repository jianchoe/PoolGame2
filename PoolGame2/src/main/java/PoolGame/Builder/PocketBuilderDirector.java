package PoolGame.Builder;

import PoolGame.Items.Pocket;
import PoolGame.Config.PocketConfig;

public class PocketBuilderDirector {

    public Pocket construct(PocketConfig config){
        PocketBuilder pBuilder = new PocketBuilderGeneral();
        pBuilder.setxPos(config.getPosConf().getX());
        pBuilder.setyPos(config.getPosConf().getY());
        pBuilder.setRadius(config.getRadius());
        return pBuilder.build();
    }
}
