
/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {

     // ADD YOUR INSTANCE VARIABLES HERE

    private int x, y, neighbooringMines;
    private boolean covered, wasClicked, mined, flagged;


    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public DotInfo(int x, int y){

        this.x = x;
        this.y = y;
        covered = true;
        wasClicked = false;
        mined = false;
        flagged = false;
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){

        return x;
    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){

        return y;
    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {

        mined = true;
    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {

        return this.mined;
    }


    /**
     * Setter for covered
     */
    public void uncover() {

        covered = false;
    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){

        return this.covered;
    }



    /**
     * Setter for wasClicked
     */
    public void click() {

        wasClicked = true;
    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {

        return wasClicked;
    }


    /**
     * Setter for neighbooringMines
     *
     * @param neighbooringMines
     *          number of neighbooring mines
     */
    public void setNeighbooringMines(int neighbooringMines) {

        this.neighbooringMines = neighbooringMines;

    }

    /**
     * Get for neighbooringMines
     *
     * @return neighbooringMines
     */
    public int getNeighbooringMines() {

        return this.neighbooringMines;

    }

    /**
     * Get for flagged
     *
     * @return flagged
     */
    public boolean isFlagged(){

        return this.flagged;
    }

    /**
     * Set button to flagged button 
     *
     */
    public void setFlagged(){

        this.flagged = true;
    }

    /**
     * Unflagged flagged button 
     *
     */
    public void unFlagged(){

        this.flagged = false;
    }
 }
