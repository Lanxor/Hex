package version2;

import java.util.Scanner;

public class Interface {
    
    public static void main(String[] args)
    {
        boolean leave;
        Game game;
        
        game = new Game();
        game = Player.loadStartPlayer(game);
        
        leave = false;
        while ( !leave )
        {
            
            switch ( Interface.menu() )
            {
                case 1: // On crée une nouvelle partie
                    System.out.println("Nous crée le jeu.");
                    game.initialize();
                    game.play();
                    game.deleteDeckC();
                    break;
                    
                case 2: // Charger une partie
                    Game.load(game);
                    game.play();
                    game.deleteDeckC();
                    System.out.println("On charger une partie.");
                    break;
                    
                case 3: // Montrer les statistique de jeu
                    System.out.println("Voici les statistiques du jeu.");
                    break;
                    
                case 4: // Personnage
                    Player.index(game);
                    break;
                    
                case 5: // Quitter
                    System.out.println("Au revoir...");
                    leave = true;
                    break;
                    
            }
        }
        
        // On quitte le programme...
        // On détruit tout
    }
    
    public static void printMenu(String[] menu)
    {
        int count;
        
        count = 1;
        System.out.println();
        for( String str : menu)
        {
            System.out.println(count++ + " : " + str);
        }
    }
    
    public static int menu()
    {
        String[] menu;
        int choice;
        
        menu = new String[5];
        menu[0] = "Créer une nouvelle partie";
        menu[1] = "Charger une partie";
        menu[2] = "Statistiques";
        menu[3] = "Joueur";
        menu[4] = "Quitter";
        Interface.printMenu(menu);
        System.out.print("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        return choice;
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
    
    public static String getString()
    {
        String str;
        Scanner keybord;
        
        keybord = new Scanner(System.in);
        str = keybord.nextLine();
        return str;
    }
}
