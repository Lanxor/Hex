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
    protected Cell[][] cell;
    
    public Cell getCell(int ord, int abs)
    {
        return this.cell[ord][abs];
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void init()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Taille du tablier : ");
        this.size = Integer.parseInt(keyboard.next());
        this.cell = new Cell[size][size];
        for (int ord = 0; ord < this.size; ++ord)
        {
            for (int abs = 0; abs < this.size; ++abs)
            {
                this.cell[ord][abs] = new Cell();
            }
        }
        System.out.println("");
    }
    
    public String displayCell(int ord, int abs)
    {
        if (this.cell[ord][abs].getColor().equals("black"))
            return "| â€¢ ";
        if (this.cell[ord][abs].getColor().equals("white"))
            return "| â—‹ ";
        return "|   ";
    }
    
    public void displayBoard()
    {
        for (int ord = 0; ord < this.size; ++ord){
            for (int abs = 0; abs < this.size; ++abs)
            {
                System.out.print(displayCell(ord, abs));
            }
            System.out.println("|");
            for (int cpt = 0; cpt < ord + 1; ++cpt)
            {
                System.out.print("  ");
            }
        }
    }
    
}