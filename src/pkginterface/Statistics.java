/**
 * @file Statistics.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des scores
 */

package pkginterface;

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
