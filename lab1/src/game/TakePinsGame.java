package game;

public class TakePinsGame {

    public static void main(String[] args) {

        Board board = new Board();
        board.setUp(10);

        Player player = new HumanPlayer("Carl");
        //Player computer = new ComputerPlayer("Computer");
        Player computer = new OtherPlayer("OtherComputer");

        System.out.println("Welcome to the Pin Game!");
        System.out.println("There are currently: " + board.getNoPins() + " left on the board.");

        while(true) {
            System.out.println("It is " + player.getUserId() + "'s turn.");
            System.out.println("There are currently: " + player.takePins(board) + " pins left on the board");

            if(board.isEmpty()){
                System.out.println(player.getUserId() + " won!");
                break;
            }

            System.out.println("It is " + computer.getUserId() + "'s turn.");
            System.out.println("There are currently: " + computer.takePins(board) + " pins left on the board");
            
            if(board.isEmpty()){
                System.out.println(computer.getUserId() + " won!");
                break;
            }
        }
    }

}
