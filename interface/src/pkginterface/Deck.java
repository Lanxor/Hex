/**
 * @file Deck.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion du tablier
 */

package pkginterface;

import java.util.Scanner;

public class Deck {
    private int size;
    protected Vertice[][] vertice;
    
    public Deck(int size)
    {
        this.size = size;
        this.vertice = new Vertice[size][size];
    }
    
    public Vertice getCell(int ord, int abs)
    {
        return this.vertice[ord][abs];
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void init()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Taille du tablier : ");
        this.size = Integer.parseInt(keyboard.next());
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
    
}