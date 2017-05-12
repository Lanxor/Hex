package version2;

import java.util.Scanner;

public class Interface {
    
    public static void main(String[] args)
    {
        int response, deckSize;
        Menu menu;
        Game game;
        
        game = new Game();
        // Chargement des deux joueurs par défaut ! ou demande de création de ces derniers
        game = Player.loadPlayer(game);
        
        // Menu principale
        do
        {
            Menu.home();
            System.out.print("Choix : ");
            switch (response = Interface.getInt(1, 5))
            {
                case 1: // On crée une nouvelle partie
                    System.out.println("Nous crée le jeu.");
                    game.initialize();
                    game.play();
                    game.deleteDeckC();
                    break;
                    
                case 2: // Charger une partie
                    //game.load();
                    game.play();
                    game.deleteDeckC();
                    System.out.println("Charger une partie.");
                    break;
                    
                case 3: // Montrer les statistique de jeu
                    System.out.println("Voici les statistiques du jeu.");
                    break;
                    
                case 4:
                    System.out.println("On change de joueur.");
                    game = Player.loadPlayer(game);
                    break;
                    
                case 5: // Quitter
                    System.out.println("Au revoir...");
                    break;
                    
            }
            System.out.println();
        } while ( response != 5 );
        
        // On quitte le programme...
        // On détruit tout
    }
    
    public static boolean isInteger(String coord) {
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
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        String input = keyboard.next();
        while (!isInteger(input) 
                || Integer.parseInt(input) < valMin 
                || Integer.parseInt(input) > valMax){
            System.out.print("Vous devez entrer un entier entre "
                    + valMin
                    + " et "
                    + valMax
                    + ", veuillez recommencer : ");
            input = keyboard.next();
        }
        return Integer.parseInt(input);
    }
    
    public static char getChar()
    {
        String str;
        Scanner keybord;
        
        keybord = new Scanner(System.in);
        str = keybord.nextLine();
        return str.charAt(0);
    }
}
