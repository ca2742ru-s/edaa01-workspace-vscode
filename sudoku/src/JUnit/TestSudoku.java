package JUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import solver.Solver;
import solver.SudokuSolver;

public class TestSudoku {

    SudokuSolver solver;
    int[][] tmp;
    
    @BeforeEach
	void setUp() {
		solver = new Solver();
        tmp = new int[9][9];
	}

	@AfterEach
	void tearDown() {
		solver = null;
        tmp = null;
	}
    
    @Test
    void testGetGrid(){
        tmp = solver.getGrid();
        assertEquals(tmp, solver.getGrid(), "Not getting the correct grid");
        tmp[0][0] = 1;
        assertEquals(tmp, solver.getGrid(), "tmp does not have the correct address after inserting 1 at [0][0]");
    }

    @Test
    void testSetGrid(){
        for(int i = 0; i < 3; i++){
            tmp[i][i+2] = 1;
            solver.set(i, i+2, 1);
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(tmp[i][j], solver.get(i, j));
            }
        }
    }

    @Test
    void testValidation(){
        solver.set(0, 0, 1);
        solver.set(2, 2, 1);
        solver.set(0, 7, 1);
        solver.set(5, 2, 1);
        assertFalse(solver.isValid(0, 0));
        assertFalse(solver.isValid(2, 2));
        assertFalse(solver.isValid(0, 7));
        assertFalse(solver.isValid(5, 2));
        assertFalse(solver.isAllValid());
    }

    @Test
    void testClearing(){
        solver.set(0, 0, 1);
        solver.set(5, 8, 2);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 0; j++){
                assertNotEquals(tmp[i][j], solver.getGrid()[i][j]);
            }
        }
        solver.clear(0, 0);
        assertEquals(tmp[0][0], solver.getGrid()[0][0]);
        solver.clearAll();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 0; j++){
                assertEquals(tmp[i][j], solver.getGrid()[i][j]);
            }
        }
    }

    @Test
    void testSolveEmptySudoku(){
        solver.setGrid(tmp);
        assertTrue(solver.solve());
        assertTrue(solver.isAllValid());

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                tmp[i][j] = 0;
            }
        }
        solver.setGrid(tmp);
        assertTrue(solver.solve());
        assertTrue(solver.isAllValid());

    }

    @Test
    void testSolveNonEmptySudoku(){
        int[][] test = new int[][]{
            {0, 0, 8, 0, 0, 9, 0, 6, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
            {1, 0, 2, 5, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 9, 0},
            {0, 5, 0, 0, 0, 0, 6, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 2, 8},
            {4, 1, 0, 6, 0, 8, 0, 0, 0},
            {8, 6, 0, 0, 3, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 4, 0, 0}
    };
        solver.setGrid(test);
        assertTrue(solver.solve());
    }

    @Test
    void testUnsolvableSudoku(){
        tmp[0][0] = 1;
        tmp[0][1] = 1;
        assertFalse(solver.solve());
    }
}
