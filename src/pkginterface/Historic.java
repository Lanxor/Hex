package pkginterface;

import java.util.ArrayList;

public class Historic {
    
    private ArrayList<Move> moves;
    private int numberOfMove;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     */
    public Historic()
    {
        this.moves = new ArrayList<>();
        this.numberOfMove = 0;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Functions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @return 
     */
    public ArrayList getMoves()
    {
        return this.moves;
    }
    
    /**
     * 
     * @return 
     */
    public int getNumberOfMove()
    {
        return this.numberOfMove;
    }
    
    /**
     * 
     * @param numberOfMove 
     */
    public void setNumberOfMove(int numberOfMove)
    {
        this.numberOfMove = numberOfMove;
    }
    
    /**
     * 
     * @param moves 
     */
    public void setMoves(ArrayList<Move> moves)
    {
        this.moves = moves;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                     Functions Getters, Setters advenced *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @param move
     * @param number 
     */
    public void setMove(Move move, int number)
    {
        this.moves.set(number-1, move);
    }
    
    /**
     * 
     * @param number
     * @return 
     */
    public Move getMove(int number)
    {
        return (Move)this.moves.get(number-1);
    }
    
    /**
     * 
     * @return 
     */
    public Move getLastMove()
    {
        return (Move)this.getMove(this.numberOfMove);
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Functions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @param move 
     */
    public void addMove(Move move)
    {
        this.moves.add(this.numberOfMove++, move);
    }
    
    /**
     * 
     * @return 
     */
    public Move deleteLastMove()
    {
        Move move;
        
        move = this.getLastMove();
        this.moves.remove(this.numberOfMove-1);
        --this.numberOfMove;
        return move;
    }
    
    /**
     * 
     */
    public void clean()
    {
        this.moves.clear();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        String str = "";
        int count = 0;
                
        while ( count < this.numberOfMove )
        {
            str += ((Move)this.moves.get(count++)).toString();
            str += "\n";
        }
        return str;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                    Functions validation *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @return 
     */
    public boolean isEmpty()
    {
        return this.numberOfMove == 0;
    }
}
