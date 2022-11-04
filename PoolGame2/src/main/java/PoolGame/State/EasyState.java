package PoolGame.State;

public class EasyState implements DifficultyState {
    @Override
    public String changeDifficulty(){
        return "/config_easy.json";
    }
}
