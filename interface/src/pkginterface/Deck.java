/**
 * @file Deck.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion du tablier
 */

package pkginterface;

public class Deck {
    private int size;
    protected Vertice[][] vertice;
    
    public Deck(int size)
    {
        this.size = size;
        this.vertice = new Vertice[size][size];
    }
    
    public void setSize(int size)
    {
        this.size = size;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void init()
    {
        for (int ord = 0; ord < this.size; ++ord)
        {
            for (int abs = 0; abs < this.size; ++abs)
            {
                this.vertice[ord][abs] = new Vertice();
            }
        }
        System.out.println("");
    }
    
    public void displayDeck()
    {
        for (int ord = 0; ord < this.size; ++ord){
            for (int abs = 0; abs < this.size; ++abs)
            {
                System.out.print(this.vertice[ord][abs].displayVertice());
            }
            System.out.println("|");
            for (int cpt = 0; cpt < ord + 1; ++cpt)
            {
                System.out.print("  ");
            }
        }
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