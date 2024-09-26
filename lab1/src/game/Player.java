package game;

public abstract class Player {
    String userId;

    protected Player(String userId){
        this.userId = userId;
    }

    protected String getUserId(){
        return userId;
    }

    //To be implemented by subclasses
    protected abstract int takePins(Board board);
}
