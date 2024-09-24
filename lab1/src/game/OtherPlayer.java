package game;

public class OtherPlayer extends Player{

    boolean choice;

    public OtherPlayer(String userId){
        super(userId);
        this.choice = false;
    }

    public int takePins(Board board){
        int noPins;
        if(choice){
            noPins = 1;
            choice = false;
        }
        else{
            noPins = 2;
            choice = true;
        }
        board.takePins(noPins);
        System.out.println(userId + " removed " + noPins + " from the board.");
        return board.getNoPins();
    }

}
