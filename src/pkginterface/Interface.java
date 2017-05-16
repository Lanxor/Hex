package pkginterface;

import java.util.Scanner;

public class Interface {
    
    /***************************************************************************
     *                                                                         *
     *                                                           Function Main *
     *                                                                         *
     **************************************************************************/
    
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
                    game.index();
                    break;
                    
                case 2: // Charger une partie
                    game.indexLoad();
                    break;
                    
                case 3: // Montrer les statistique de jeu
                    Interface.showMessage("Voici les statistiques du jeu.\n");
                    break;
                    
                case 4: // Personnage
                    Player.index(game);
                    break;
                    
                case 5: // Quitter
                    Interface.showMessage("Au revoir...\n");
                    leave = true;
                    break;
                    
            }
        }
    }
    
    /***************************************************************************
     *                                                                         *
     *                                               Function Static main menu *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @return 
     */
    public static int menu()
    {
        String[] menu;
        int choice;
        
        menu = new String[5];
        menu[0] = "Créer une nouvelle partie\n";
        menu[1] = "Charger une partie\n";
        menu[2] = "Statistiques\n";
        menu[3] = "Joueur\n";
        menu[4] = "Quitter\n";
        Interface.printMenu(menu);
        System.out.print("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        return choice;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                 Function Static display *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @param menu 
     */
    public static void printMenu(String[] menu)
    {
        int count;
        
        count = 1;
        System.out.println();
        for( String str : menu)
        {
            Interface.showMessage(count++ + " : " + str);
        }
    }
    
    /**
     * 
     * @param str 
     */
    public static void showMessage(String str)
    {
        System.out.print(str);
    }
    
    /***************************************************************************
     *                                                                         *
     *                                        Function Static interface getter *
     *                                                                         *
     **************************************************************************/
    /**
     * 
     * @param coord
     * @return 
     */
    public static boolean isInteger(String coord) {
       try
       { 
           Integer.parseInt(coord); 
       }
       catch(NumberFormatException nfe) { return false; }
       
       return true;
    }
    
    /**
     * 
     * @param valMin
     * @param valMax
     * @return 
     */
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
    
    /**
     * 
     * @return 
     */
    public static char getChar()
    {
        String str;
        Scanner keybord;
        
        keybord = new Scanner(System.in);
        str = keybord.nextLine();
        return str.charAt(0);
    }
    
    /**
     * 
     * @return 
     */
    public static String getString()
    {
        String str;
        Scanner keybord;
        
        keybord = new Scanner(System.in);
        str = keybord.nextLine();
        return str;
    }
}
