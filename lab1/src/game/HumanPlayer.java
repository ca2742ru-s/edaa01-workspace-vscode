package game;

import java.util.Scanner;

public class HumanPlayer extends Player{
    Scanner scan;
    public HumanPlayer(String userid){
        super(userid);
        scan = new Scanner(System.in);
    }

    //Takes 1 or 2 pins from the board, based on user input
    public int takePins(Board board){
        
        while(true){
            System.out.print("Please input number of pins you would like to remove from the board: ");
            
            try {
                int noPins = scan.nextInt();

                /*
                    Removes 1 or 2 pins from the board based on user input.
                    Numbers outside this range, or illegal characters, is not accepted. 
                */
                if(1 <= noPins && noPins <= 2){
                    board.takePins(noPins);
                    System.out.println(userId + " removed " + noPins + " from the board.");
                    return board.getNoPins();
                }
                else if(board.getNoPins() - noPins < 0){
                    System.out.println("You cannot remove more pins than there are on the table! Please try again.");
                }
                else{
                    System.out.println("You have entered a non-allowed number. You can remove 1 or 2 pins per round. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("You have entered a non-allowed input. You can remove 1 or 2 pins per round.");
                scan.nextLine();    //Needed to flush the next input line and not get stuck in infinite loop
            }
        }
    }
}
