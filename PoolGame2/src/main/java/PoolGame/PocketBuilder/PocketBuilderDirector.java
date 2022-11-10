package PoolGame.PocketBuilder;

import PoolGame.Items.Pocket;
import PoolGame.Config.PocketConfig;

public class PocketBuilderDirector {
    /**
     * Builds the pocket from information parsed from
     * the pocket config
     * @param config
     * @return Built pocket
     */
    public Pocket construct(PocketConfig config){
        PocketBuilder pBuilder = new PocketBuilderGeneral();
        pBuilder.setxPos(config.getPosConf().getX());
        pBuilder.setyPos(config.getPosConf().getY());
        pBuilder.setRadius(config.getRadius());
        return pBuilder.build();
    }
}
