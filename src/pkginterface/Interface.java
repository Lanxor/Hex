/**
 * @file Deck.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe principale
 */

package pkginterface;

import java.util.Scanner;

public class Interface {
    
    private static boolean isInteger(String coord) {
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
    
    public static int getInt(int valMin, int valMax)
    {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.next();
        while (!isInteger(input) 
                || Integer.parseInt(input) < valMin 
                || Integer.parseInt(input) > valMax){
            System.out.print("Vous devez entrer un entier entre "
                    + Integer.toString(valMin)
                    + " et "
                    + Integer.toString(valMax)
                    + ", veuillez recommencer : ");
            input = keyboard.next();
        }
        return Integer.parseInt(input);
    }

    public static void main(String[] args)
    {
        int response;
        Menu menu;
        Game game;
        
        do
        {
            Menu.home();
            System.out.print("Choix : ");
            switch (response = Interface.getInt(1, 4))
            {
                case 1:
                    System.out.println("Nous crée le jeu.");
                    System.out.print("Taille du jeu : ");
                    game = new Game(getInt(2, 50), new Player('b'), new Player('w'));
                    game.gamePlay();
                    break;
                    
                    
                case 2: // Charger une partie
                    System.out.println("Charger une partie.");
                    game = Game.loadGame();
                    break;
                    
                    
                case 3: // Montrer les statistique de jeu
                    System.out.println("Voici les statistiques du jeu.");
                    break;
                    
                    
                case 4: // Quitter
                    System.out.println("Au revoir...");
                    break;
                    
                    
            }
            System.out.println();
        } while ( response != 4 );
    }
    
}
