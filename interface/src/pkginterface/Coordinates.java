/**
 * @file Coordinates.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des Coordonnées
 */
package pkginterface;

import java.util.Scanner;

public class Coordinates {
    
    private Deck deck;
    private int abscisse;
    private int ordonnee;
    
    public Coordinates(Deck deck){
        this.deck = deck;
        this.abscisse = 0;
        this.ordonnee = 0;
    }
    
    private boolean isInteger(String coord) {
       boolean isValid = true;
       try
       { 
           Integer.parseInt(coord); 
       }
       catch(NumberFormatException nfe)
       { 
           isValid = false;
       }
       return isValid;
   }
    
    public void setCoord()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("abs : ");
        String input = keyboard.next();
        while (!isInteger(input) 
                || Integer.parseInt(input) < 0 
                || Integer.parseInt(input) >= this.deck.getSize()){
            System.out.print("Vous devez entrer un entier entre 0 et " 
                    + Integer.toString(this.deck.getSize()-1)
                    + ", veuillez recommencer.\n abs : ");
            input = keyboard.next();
        }
        this.abscisse = Integer.parseInt(input);
        System.out.print("ord : ");
        input = keyboard.next();
        while (!isInteger(input) 
                || Integer.parseInt(input) < 0 
                || Integer.parseInt(input) >= this.deck.getSize()){
            System.out.print("Vous devez entrer un entier entre 0 et " 
                    + Integer.toString(this.deck.getSize()-1)
                    + ", veuillez recommencer.\n abs : ");
            input = keyboard.next();
        }
        this.ordonnee = Integer.parseInt(input);
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