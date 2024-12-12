package solver;

public class Solver implements SudokuSolver{

    private int[][] grid;
    private boolean preCheck;

    public Solver(){
        //Create empty grid
        grid = new int[9][9];
    }

    @Override
    public boolean solve() {
        if(preCheck){
            return solve(0, 0);
        }
        else
            return false;
    }

    //Recursive helper method to solve Sudoku board
    private boolean solve(int row, int col){

        //If current box has digit then validate
        if(grid[row][col] != 0){
            if(isValid(row, col)){
                //If the last digit is valid then return true and complete
                if(row == 8 && col == 8)
                    return true;
                //If at the last column, advance to next row and go back to first col
                else if(col == 8)
                    return solve(row + 1, 0);
                //Advance to next col
                else
                    return solve(row, col + 1);
            }
            //If not valid return false and go back to last recursive step
            else{
                return false;
            }   
        }
        //If box is empty then try digit 1..9
        else{
            for(int i = 1; i < 10; i++){
                grid[row][col] = i;
                if(isValid(row, col)){
                    if(row == 8 && col == 8)
                        return true;
                    else if(col == 8){
                        //If next step is false this iteration must continue with next digit
                        if(!solve(row + 1, 0))
                            continue;
                        else
                            return true;
                    }
                    else{
                        //If next step is false this iteration must continue with next digit
                        if(!solve(row, col + 1))
                            continue;
                        else
                            return true;
                    }                        
                }
            }
            //If all digits have been explored then set current to 0 and go back to previous recursive step
            grid[row][col] = 0;
            return false;
        }
    }

    @Override
    //Method to set digit into current box at [row][col]
    public void set(int row, int col, int digit) {
        //If digit is out of bounds throw IllegalArgumentException
        if(digit < 1 || digit > 9)
            throw new IllegalArgumentException("Digit must be within the range 1 to 9");
        //If row/col range is out of bounds throw IndexOutOfBoundsException
        if(row < 0 || row > 8 || col < 0 || col > 8)
            throw new IndexOutOfBoundsException("Grid bounds range from 0 to 8");
        //Set digit into row/col box
        grid[row][col] = digit;
    }

    @Override
    //Method to return current [row][col] digit
    public int get(int row, int col) {
        if(row < 0 || row > 8 || col < 0 || col > 8)
            throw new IndexOutOfBoundsException("Grid bounds range from 0 to 8");

        return (int) grid[row][col];
    }

    @Override
    //Method to clear current [row][col] digit
    public void clear(int row, int col) {
        grid[row][col] = 0;
    }

    @Override
    //Method to clear entire Sudoku board
    public void clearAll() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j] = 0;
            }
        }
    }

    @Override
    //Method to check if current digit at [row][col] is valid according to the Sudoku rules
    public boolean isValid(int row, int col) {
        if(row < 0 || row > 8 || col < 0 || col > 8)
            throw new IndexOutOfBoundsException("Grid bounds range from 0 to 8");

        //True if box is empty (represented by a 0)
        if(grid[row][col] == 0)
            return true;
        
        else{
            int r1 = row;
            int c1 = col;

            //Find first row index for region (3x3)
            while(r1%3!=0){
                r1 -= 1;
            }

            //Find first col index for region (3x3)
            while(c1%3 != 0){
                c1 -= 1;
            }

            //Iterate through row. Skip empty boxes and current digit that is being validated
            for(int i = 0; i < 9; i++){
                if(i == col || grid[row][i] == 0)
                    continue;
                if(grid[row][i] == grid[row][col])
                    return false;
            }
            //Iterate through col. Skip empty boxes and current digit that is being validated
            for(int i = 0; i < 9; i++){
                if(i == row || grid[i][col] == 0)
                    continue;
                if(grid[i][col] == grid[row][col])
                    return false;
            }
            //Iterate through region (3x3)
            for(int i = r1; i < r1+3; i++){
                for(int j = c1; j < c1+3; j++){
                    if(i == row && j == col || grid[i][j] == 0)
                        continue;
                    if(grid[i][j] == grid[row][col])
                        return false;
                }
            }
            return true;
        }  
    }

    @Override
    //Method to check if entire Sudoku board is valid according to the Sudoku rules
    public boolean isAllValid() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(!isValid(i, j))
                    return false;
            }
        }
        return true;
    }

    //Help method to check if the given grid is valid according to the Sudoku rules
    private boolean preCheckGrid(){
        if(!isAllValid())
            return false;
        return true;
    }

    @Override
    //Method to set Sudoku board based on the grid m given
    public void setGrid(int[][] m) {
        if(m[0].length > 9 || m[1].length > 9){
            throw new IllegalArgumentException("Number of rows and cols cannot exceed 9");
        };
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(m[i][j] > 9 || m[i][j] < 0){
                    throw new IllegalArgumentException("Numbers must be in the range 1 to 9");
                }
                grid[i][j] = m[i][j];
            }
        }
        preCheck = preCheckGrid();
    }

    @Override
    //Method to return current Sudoku board
    public int[][] getGrid() {
        return grid;
    }
    
    
}
