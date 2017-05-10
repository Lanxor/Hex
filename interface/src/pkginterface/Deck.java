/**
 * @file Deck.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion du tablier
 */

package pkginterface;

public class Deck {
    private int size;
    
    public Deck(int size)
    {
        this.size = size;
    }
    
    public void setSize(int size)
    {
        this.size = size;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public String toString()
    {
        String str = "";
        str += "\\board";
        for ( int abscisse = 0; abscisse < this.size; ++abscisse )
        {
            for (int ordonnee = 0; ordonnee < this.size; ++ordonnee )
            {
                // Appelle de la fonction getVertice du C
                // et un getColor suffit
                // pour avoir le format du PDF
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        str += "\\endboard";
        
        return str;
    }
    
}