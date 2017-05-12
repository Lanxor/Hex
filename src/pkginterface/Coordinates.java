/**
 * @file Coordinates.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des Coordonnées
 */

package pkginterface;

public class Coordinates {
    
    private int abscisse;
    private int ordonnee;
    
    public Coordinates(int abs, int ord){
        this.abscisse = abs;
        this.ordonnee = ord;
    }
    
    public static Coordinates askCoordinates(int min, int max)
    {
        int abscisse, ordonnee;
        
        System.out.println("Veuillez entrer des coordonnées.");
        
        System.out.print("abs : ");
        abscisse = Interface.getInt(min, max);
        
        System.out.print("ord : ");
        ordonnee = Interface.getInt(min, max);
        
        return new Coordinates(abscisse, ordonnee);
    }
    
    public void setAbscisse(int abscisse)
    {
        this.abscisse = abscisse;
    }
    
    public int getAbscisse()
    {
        return this.abscisse;
    }
    
    public void setOrdonnee(int ordonnee)
    {
        this.ordonnee = ordonnee;
    }
    
    public int getOrdonnee()
    {
        return this.ordonnee;
    }
    
    public String toString()
    {
        return this.getAbscisse() + " " + this.getOrdonnee();
    }
}