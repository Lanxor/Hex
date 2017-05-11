/**
 * @file Historic.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion de l'historique
 */

package pkginterface;

import java.util.ArrayList;

public class Historic {
    
    private ArrayList moves;
    private int numberOfMove;
    
    public Historic()
    {
        this.moves = new ArrayList();
        this.numberOfMove = 0;
    }
    
    public void deleteLastMove()
    {
        this.moves.remove(this.numberOfMove--);
    }
    
    public Move getLastMove()
    {
        return (Move)this.moves.get(this.numberOfMove);
    }
    
    public void addMove(Move move)
    {
        this.moves.add(this.numberOfMove++, move);
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
