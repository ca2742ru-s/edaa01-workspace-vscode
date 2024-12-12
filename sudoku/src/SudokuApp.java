import graphic.SudokuView;
import solver.Solver;
import solver.SudokuSolver;

public class SudokuApp {
    public static void main(String[] args) throws Exception {
        SudokuSolver solver = new Solver();
        SudokuView sudokuView = new SudokuView(solver);
    }
}
