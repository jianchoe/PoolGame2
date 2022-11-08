package PoolGame.State;

public class OriginalState implements DifficultyState {
    @Override
    public String changeDifficulty(){
        return "/config.json";
    }
}
