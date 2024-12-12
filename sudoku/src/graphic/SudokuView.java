package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import solver.SudokuSolver;

public class SudokuView {

    private SudokuSolver solver;
    private JTextField[][] textFields;

    public SudokuView(SudokuSolver sudokuSolver){
        this.solver = sudokuSolver;
        this.textFields = new JTextField[9][9];

        SwingUtilities.invokeLater(() -> createWindow("Sudoku Application", 300, 400));
    }

    private void createWindow(String title, int width, int height){

        //Create frame and set it up
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);

        //Panels to be used for buttons and the Sudoku digit grid
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));

        //Setup of Sudoku grid
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                textFields[i][j] = new JTextField(1);
                textFields[i][j].setFont(new Font(Font.DIALOG, Font.TYPE1_FONT, 20));
                int color = (i/3) + (j/3);
                if(color%2 == 0)
                    textFields[i][j].setBackground(Color.LIGHT_GRAY);
                else
                    textFields[i][j].setBackground(Color.WHITE);
                gridPanel.add(textFields[i][j]);
            }
        }
        
        //Buttons to solve Sudoku and clear the board
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        //Setting listeners to buttons
        clearButton.addActionListener(e -> {
            wipeGrid(gridPanel, textFields, solver);
        });

        solveButton.addActionListener(e -> {
            try {
                solve(textFields, solver);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
            
        });
        
        //Add buttons to button panel
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        
        //Add panels to frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        
        //Show frame
        frame.setVisible(true);

    }

    //Method to clear all digit from the Sudoku grid
    private void wipeGrid(JPanel gridPanel, JTextField[][] textFields, SudokuSolver solver){
        gridPanel.removeAll();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                textFields[i][j].setText(null);
                solver.clearAll();
                gridPanel.add(textFields[i][j]);
            }
        }
    }

    //Method to solve the Sudoku board based on the current grid
    private void solve(JTextField[][] textFields, SudokuSolver solver){
        
        /* Capture the characters in the text fields, cast them to int and insert into a temporary matrix
        /* If no character is present, fill it as a 0
        */
        int[][] tmp = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(!textFields[i][j].getText().isEmpty()){
                    try {
                        tmp[i][j] = (int) Integer.parseInt(textFields[i][j].getText());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Sudoku only accepts integers between 1..9 inside the boxes");
                        textFields[i][j].setText(null);
                        return;
                    }
                }
                else
                    tmp[i][j] = 0;
            }
        }

        //Try to solve current grid
        solver.setGrid(tmp);
        if(solver.solve()){
            tmp = solver.getGrid();
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    textFields[i][j].setText(String.valueOf(tmp[i][j]));
                }
            }
        }
        else{
            //If no solution was found, show user with a message dialog
            JOptionPane.showMessageDialog(null, "No solution was found");
        }
            
    }
      
}
