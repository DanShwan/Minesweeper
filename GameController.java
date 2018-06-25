import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.*;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, MouseListener {

    // ADD YOUR INSTANCE VARIABLES HERE

    private GameModel gameModel;
    private GameView gameView;


    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param heigth
     *            the heigth of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int heigth, int numberOfMines) {

        gameModel = new GameModel(width,heigth,numberOfMines);
        gameView = new GameView(gameModel, this);
    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Reset")){

            reset();

        }
        else if(e.getActionCommand().equals("Quit")){

            System.exit(0);

        }
        else{

            for(int i = 0; i < this.gameModel.getHeigth(); i++){

                for(int j = 0; j < this.gameModel.getWidth(); j++){

                    if(((DotButton)e.getSource()).getName().equals(i+","+j)){

                        if(gameModel.isFlagged(j,i) != true){

                            play(j,i);

                        }
                    }
                }
            }
        }
    }

    /**
     * resets the game
     */
    private void reset(){

        gameModel.reset();
        gameView.update();
    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

        if(gameModel.isCovered(width,heigth) == true){

            if(gameModel.get(width,heigth).isMined() == true){

                gameModel.click(width,heigth);
                gameModel.uncoverAll();
                gameView.update();

                Object[] optionButton = {"Play Again", "Quit"};
                
                int option = JOptionPane.showOptionDialog(null, "Ouf! You've lost in " + gameModel.getNumberOfSteps() + " steps!\nWould you like to play again?", "BOOM!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionButton, optionButton[0]);
                
                if(option == 0){

                    reset();
                }
                else{

                    System.exit(0);
                }
            }
            else{
                
                if(gameModel.isBlank(width, heigth) == true){

                    clearZone(gameModel.get(width, heigth));
                    gameModel.click(width,heigth);
                    gameModel.step();
                    gameView.update();
                }
                else{
                    gameModel.uncover(width, heigth);
                    gameModel.get(width,heigth).uncover();
                    gameModel.step();
                    gameModel.click(width,heigth);
                    gameView.update();
                }
            }      
        }
    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {

        GenericArrayStack<DotInfo> stack = new GenericArrayStack<DotInfo>(5);

        stack.push(initialDot);

        while(!stack.isEmpty()){

            DotInfo d = stack.pop();
            for(int x = -1; x < 2; x++){

                for(int y = -1; y < 2; y++){

                    try{
                        if(gameModel.get(d.getY()+y,d.getX()+x).isCovered() == true){

                            gameModel.uncover(d.getY()+y,d.getX()+x);

                            if(gameModel.isBlank(d.getY()+y,d.getX()+x) == true){

                                stack.push(gameModel.get(d.getY()+y,d.getX()+x));
                            }
                        }
                    }catch (ArrayIndexOutOfBoundsException e){

                    }
                }
            }
        } 
    }
    
    /**
     * Callback used when the user right clicks to set flag.
     *
     * @param e
     *            the MouseEvent
     */
    public void mouseClicked(MouseEvent e) { 

          if(e.getButton() == MouseEvent.BUTTON3) {

            for(int i = 0; i < this.gameModel.getHeigth(); i++){

                for(int j = 0; j < this.gameModel.getWidth(); j++){

                    if(((DotButton)e.getSource()).getName().equals(i+","+j)){

                        if(gameModel.isCovered(j,i) == true && gameModel.isFlagged(j,i) != true){
    
                            gameModel.get(j,i).setFlagged();
                            gameView.update();

                        }
                        else{

                            gameModel.get(j,i).unFlagged();
                            gameView.update();
                        }
                    }
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}
