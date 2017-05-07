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
    
    public Coordinates(int abs, int ord){
        this.abscisse = abs;
        this.ordonnee = ord;
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