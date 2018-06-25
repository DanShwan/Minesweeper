import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE

    private DotButton[][] board;
    private GameModel gameModel;
    private JLabel nStepsLabel; 

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController){
        
        this.gameModel = gameModel;
        this.board = new DotButton[this.gameModel.getHeigth()][this.gameModel.getWidth()];

        setTitle("Minesweeper - ITI1121 Assignment");

        JPanel gamePanel = new JPanel(new GridLayout(this.gameModel.getHeigth(), this.gameModel.getWidth()));
        add(gamePanel, BorderLayout.CENTER);

        for(int i = 0; i < this.gameModel.getHeigth(); i++){

            for(int j = 0; j < this.gameModel.getWidth(); j++){

                board[i][j] = new DotButton(i,j,11);
                board[i][j].setName(i+","+j);
                board[i][j].setBorder(null);
                board[i][j].addActionListener(gameController);
                board[i][j].addMouseListener(gameController);
                gamePanel.add(board[i][j]);
            }
        }

        JPanel stepPanel = new JPanel();
        add(stepPanel, BorderLayout.SOUTH);

        nStepsLabel = new JLabel("Number of steps: " + Integer.toString(this.gameModel.getNumberOfSteps()));
        stepPanel.add(nStepsLabel, BorderLayout.WEST);
        
        JButton reset = new JButton("Reset");
        reset.addActionListener(gameController);
        stepPanel.add(reset, BorderLayout.SOUTH);

        JButton quit = new JButton("Quit");
        quit.addActionListener(gameController);
        stepPanel.add(quit, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){

        nStepsLabel.setText("Number of steps: " + Integer.toString(this.gameModel.getNumberOfSteps()));

        for(int i = 0; i < this.gameModel.getHeigth(); i++){

            for(int j = 0; j < this.gameModel.getWidth(); j++){

                board[i][j].setIconNumber(getIcon(j,i));

            }
        }

        if(gameModel.isFinished() == true){

            Object[] optionButton = {"Play Again", "Quit"};
                
            int option = JOptionPane.showOptionDialog(null, "Hooray! You've beaten the game in " + gameModel.getNumberOfSteps() + " steps!\nWould you like to play again?", "CONGRATULATIONS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionButton, optionButton[0]);
                
            if(option == 0){

                gameModel.reset();
                update();
            }
            else{

                System.exit(0);
            }
        }
    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){

        if(gameModel.get(i,j).isCovered() == true){

            if(gameModel.isFlagged(i,j) == true){

                return 12;
            }
            else{

                return 11;
            }
        }
        else{
            if(gameModel.get(i,j).isMined() == true){

                if(gameModel.get(i,j).hasBeenClicked() == true){

                    return 10;
                }
                else{
                    return 9;
                }
            }
            else{
                
                switch(gameModel.get(i,j).getNeighbooringMines()) {
                    case 1 : return 1;
                    case 2 : return 2;
                    case 3 : return 3;
                    case 4 : return 4;
                    case 5 : return 5;
                    case 6 : return 6;
                    case 7 : return 7;
                    case 8 : return 8;
                    default: return 0;
                }
            }
        }
    }
}
