/**
 * @file Historic.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion de l'historique
 */

package pkginterface;

public class Historic {
    
    private Move[] move;
    
    public Move getLastMove()
    {
        return this.move[this.move.length-1];
    }
    
    public void addMove(Move m)
    {
        this.move[this.move.length] = m;
    }
    
}
