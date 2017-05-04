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
public class Statistics {
    
    private int nbVictory;
    private int nbDefeats;
    
    public Statistics()
    {
        this.nbVictory = 0;
        this.nbDefeats = 0;
    }
    
    public int getNbVictories()
    {
        return this.nbVictory;
    }
    
    public int getNbDefeats(){
        return this.nbDefeats;
    }
    
    public void addVictory()
    {
        ++this.nbVictory;
    }
    
    public void addDefeat()
    {
        ++this.nbDefeats;
    }
    
}
