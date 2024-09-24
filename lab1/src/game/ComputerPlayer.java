package game;

public class ComputerPlayer extends Player{

    public ComputerPlayer(String userId){
        super(userId);
    }

    public int takePins(Board board){
        int pinsLeft = board.getNoPins();
        int noPins;
        while(true){
            while(true){
                noPins = (int)Math.rint(Math.random()) + 1;
                if(pinsLeft - noPins >= 0){
                    break;
                }
            }
            board.takePins(noPins);
            System.out.println(userId + " removed " + noPins + " from the board.");
            return board.getNoPins();
        }
    }

}