package version2;

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
    
    public void setAbscisse(int abscisse)
    {
        this.abscisse = abscisse;
    }
    
    public void setOrdonnee(int ordonnee)
    {
        this.ordonnee = ordonnee;
    }
    
    public static Coordinates askCoordinates(int min, int max)
    {
        int abscisse, ordonnee;
        Coordinates coordinates;
        
        System.out.println("Veuillez entrer des coordonnées.");
        System.out.print("abs : ");
        abscisse = Interface.getInt(min, max);
        System.out.print("ord : ");
        ordonnee = Interface.getInt(min, max);
        coordinates = new Coordinates(abscisse, ordonnee);
        return coordinates;
    }
    
    public String toString()
    {
        return this.getAbscisse() + " " + this.getOrdonnee();
    }
}