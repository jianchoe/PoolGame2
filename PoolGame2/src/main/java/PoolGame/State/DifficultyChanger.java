package PoolGame.State;

public class DifficultyChanger {

    private DifficultyState state;

    public void setState(DifficultyState state){
        this.state = state;
    }
    public String getState(){
        return state.changeDifficulty();
    }
}
