package game;

public class Board {

    private int noPins;

    public Board(){
    }

    //Sets up board with number of pins noPins
    public void setUp(int noPins){
        this.noPins = noPins;
    }

    //Return number of pins noPins
    public int getNoPins(){
        return noPins;
    }

    //Remove pins from the board
    public int takePins(int noPins){
        this.noPins -= noPins;
        return this.noPins;
    }

    //Returns true if board is empty, otherwise false
    public boolean isEmpty(){
        if(noPins == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
