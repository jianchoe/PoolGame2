package PoolGame.State;

public class HardState implements DifficultyState {
    @Override
    public String changeDifficulty(){
        return "/config_hard.json";
    }
}
