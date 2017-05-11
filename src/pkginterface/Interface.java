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
    
    public static Coordinates getCoordinates(Deck deck)
    {
        System.out.println("Veuillez entrer des coordonnées.");
        System.out.print("abs : ");
        int abs = Interface.getInt(0, deck.getSize() - 1);
        System.out.print("ord : ");
        int ord = Interface.getInt(0, deck.getSize() - 1);
        Coordinates c = new Coordinates(abs, ord);
        return c;
    }


    public static void main(String[] args)
    {
        int response, round, choice;
        boolean leaveGame;
        Menu menu;
        Game game;
        Coordinates coordinates;
        
        do
        {
            Menu.home();
            System.out.print("Choix : ");
            switch (response = Interface.getInt(1, 4))
            {
                case 1:
                    System.out.println("Vous jouer !!");
                    System.out.println("Nous crée le jeu.");
                    System.out.print("Taille du jeu : ");
                    game = new Game(getInt(2, 50), new Player('b'), new Player('w'));
                    round = 0;
                    leaveGame = false;
                    do
                    {
                        game.showDeck();
                        Menu.choice(round);
                        System.out.print("Choix : ");
                        choice = getInt(1, 5);
                        switch ( choice )
                        {
                            case 1: // On joue
                                
                                game.playMove();
                                System.out.println("Au joueur suivant de jouer...");
                                game.switchPlayer();
                                break;
                            case 3: // Sauvegarder
                                System.out.println(game.toStringFileSave());
                                //game.saveInFile();
                                break;
                            case 4: // Sauvegarder et Quitter
                                leaveGame = true;
                                break;
                            case 5: // Quitter sans sauvegarder
                                leaveGame = true;
                                break;
                        }
                        System.out.println();
                    } while ( !leaveGame );
                    break;
                    
                    
                case 2: // Charger une partie
                    System.out.println("Charger une partie.");
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
