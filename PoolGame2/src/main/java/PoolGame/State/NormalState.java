package PoolGame.State;

public class NormalState implements DifficultyState {
    @Override
    public String changeDifficulty(){
        return "/config_normal.json";
    }
}
