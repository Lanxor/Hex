/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

/**
 *
 * @author Eléana
 */
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
