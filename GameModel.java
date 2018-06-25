import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel{


     // ADD YOUR INSTANCE VARIABLES HERE
    private int widthOfGame, heigthOfGame, numberOfMines, numberOfSteps, numberUncovered;
    private DotInfo[][] model;
    private static java.util.Random generator = new java.util.Random();

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        
    // ADD YOU CODE HERE

        int countMines = 0, xTemp, yTemp;

        this.widthOfGame = width;
        this.heigthOfGame = heigth;
        this.numberOfMines = numberOfMines;
        numberOfSteps = 0;
        this.model = new DotInfo[heigthOfGame][widthOfGame];
        this.numberUncovered = 0;

        for(int i = 0; i < this.heigthOfGame; i++){

            for (int j = 0; j < this.widthOfGame; j++){
                
                model[i][j] = new DotInfo(i,j);
            }
        }

        while(countMines < this.numberOfMines){

            xTemp = generator.nextInt(this.widthOfGame);
            yTemp = generator.nextInt(this.heigthOfGame);

            if(model[yTemp][xTemp].isMined() != true){

                model[yTemp][xTemp].setMined();
                countMines++;
            }
        }

        for(int i = 0; i < this.widthOfGame; i++){

            for (int j = 0; j < this.heigthOfGame; j++){
                
                model[j][i].setNeighbooringMines(getNeighbooringMines(i,j));
            }
        }
    }

 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){

    // ADD YOU CODE HERE

        numberOfSteps = 0;
        numberUncovered = 0;
        int countMines = 0;
        int temp = 0;
        int xTemp, yTemp;

        for(int i = 0; i < this.heigthOfGame; i++){

            for (int j = 0; j < this.widthOfGame; j++){
                
                model[i][j] = new DotInfo(i,j);
            }
        }

        while(temp < this.numberOfMines){

            xTemp = generator.nextInt(this.widthOfGame);
            yTemp = generator.nextInt(this.heigthOfGame);

            if(model[yTemp][xTemp].isMined() != true){

                model[yTemp][xTemp].setMined();
                temp++;
            }
        }
        for(int i = 0; i < this.widthOfGame; i++){

            for (int j = 0; j < this.heigthOfGame; j++){
                
                model[j][i].setNeighbooringMines(getNeighbooringMines(i,j));
            }
        }
    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        
    // ADD YOU CODE HERE

        return this.heigthOfGame;

    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        
    // ADD YOU CODE HERE

        return this.widthOfGame;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        
    // ADD YOU CODE HERE

        return model[j][i].isMined();

    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE

        return model[j][i].hasBeenClicked();

    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        
    // ADD YOU CODE HERE

        if(getNeighbooringMines(i,j) == 0){

            return true;
        }

        return false;

    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE

        return model[j][i].isCovered();

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE

        int countMines = 0;

        for(int x = -1; x < 2; x++){

            for(int y = -1; y < 2; y++){

                try{

                    if(model[j+y][i+x].isMined() == true){

                        countMines++;
                    }
        
                }catch (ArrayIndexOutOfBoundsException e){

                }
            }
        }
        return countMines;

    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE

        model[j][i].uncover();
        this.numberUncovered++;

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE

        model[j][i].click();

    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE

        for(int i = 0; i < widthOfGame; i++){

            for(int j = 0; j < heigthOfGame; j++){

                if(model[j][i].isCovered() != false){

                    if(model[j][i].isFlagged() == true){

                        model[j][i].unFlagged();
                    }

                    model[j][i].uncover();
                    this.numberUncovered++;

                }
            }
        }
    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE

        return numberOfSteps;

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE

        return model[j][i];

    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
        
    // ADD YOU CODE HERE

        numberOfSteps++;

    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE

        if(numberUncovered == ((heigthOfGame*widthOfGame) - numberOfMines)){

            return true;

        }
        return false;
    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE

        return "Game of size: (" + getWidth() + "x" + getHeigth() + ") currently has " + numberUncovered + " uncovered";

    }

    // ADDITIONAL METHODS

    /**
     * Getter method for determining if a dot is flagged
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j].isFlagged()
     */
    public boolean isFlagged(int i, int j){

        return model[j][i].isFlagged();


    }
}
