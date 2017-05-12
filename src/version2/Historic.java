package version2;

import java.util.ArrayList;

public class Historic {
    
    private ArrayList moves;
    private int numberOfMove;
    
    public Historic()
    {
        this.moves = new ArrayList();
        this.numberOfMove = 0;
    }
    
    public Move getMove(int number)
    {
        return (Move)this.moves.get(number);
    }
    
    public void setMove(Move move, int number)
    {
        this.moves.set(number, move);
    }
    
    public void addMove(Move move)
    {
        this.moves.add(this.numberOfMove++, move);
    }
    
    public Move getLastMove()
    {
        return this.getMove(this.numberOfMove);
    }
    
    public void deleteLastMove()
    {
        this.moves.remove(this.numberOfMove--);
    }
    
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
}
