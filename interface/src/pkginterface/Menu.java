/**
 * @file Menu.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des menu
 */
package pkginterface;

import java.util.Scanner;

public class Menu {
    
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
    
    public int Home()
    {
        System.out.println("1 : Créer une nouvelle partie\n"
                + "2 : Charger une partie\n"
                + "3 : Quitter"
                + "4 : Scores");
        Scanner keyboard = new Scanner(System.in);
        String choice = keyboard.next();
        while (!isInteger(choice) 
                || Integer.parseInt(choice) < 0 
                || Integer.parseInt(choice) > 4){
            System.out.print("erreur ");
            choice = keyboard.next();
        }
        return Integer.parseInt(choice);
    }
    
    public int Choice()
    {
        System.out.println("1 : Jouer\n"
                + "2 : Revenir au coup précédent"
                + "3 : Sauvegarder\n"
                + "4 : Sauvegarder et Quitter"
                + "5 : Quitter sans sauvegarder");
        Scanner keyboard = new Scanner(System.in);
        String choice = keyboard.next();
        while (!isInteger(choice) 
                || Integer.parseInt(choice) < 0 
                || Integer.parseInt(choice) > 5){
            System.out.print("erreur ");
            choice = keyboard.next();
        }
        return Integer.parseInt(choice);
    }
    
}