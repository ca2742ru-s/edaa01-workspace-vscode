package game;

public class Board {

    private int noPins;

    public Board(){
    }

    public void setUp(int noPins){
        this.noPins = noPins;
    }

    public int getNoPins(){
        return noPins;
    }

    public int takePins(int noPins){
        this.noPins -= noPins;
        return this.noPins;
    }

    public boolean isEmpty(){
        if(noPins == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
