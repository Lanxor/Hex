/**
 * @file Coordinates.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des Coordonnées
 */
package pkginterface;

import java.util.Scanner;

public class Coordinates {
    
    private int abscisse;
    private int ordonnee;
    
    public Coordinates(){
        this.abscisse = 0;
        this.ordonnee = 0;
    }
    
    public void setCoord()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("abs : ");
        this.abscisse = Integer.parseInt(keyboard.next());
        System.out.print("ord : ");
        this.ordonnee = Integer.parseInt(keyboard.next());
    }
    
    public int getAbscisse()
    {
        return this.abscisse;
    }
    
    public int getOrdonnee()
    {
        return this.ordonnee;
    }
    
}