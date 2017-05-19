package pkginterface;

import java.util.Scanner;

public class InterfaceConsole {
    
    /***************************************************************************
     *                                                                         *
     *                                                           Function Main *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction principale du programme de jeu HEX
     * @param args 
     */
    public static void main(String[] args)
    {
        
        boolean leave;
        Game game;
        
        game = new Game();
        game = Player.loadStartPlayer(game);
        
        leave = false;
        while ( !leave )
        {
            
            switch ( InterfaceConsole.menu() )
            {
                case 1: // On crée une nouvelle partie
                    game.index();
                    break;
                    
                case 2: // Charger une partie
                    game.indexLoad();
                    break;
                    
                case 3: // Montrer les statistique de jeu
                    InterfaceConsole.showMessage("Voici les statistiques du jeu.\n");
                    Score.index();
                    break;
                    
                case 4: // Personnage
                    Player.index(game);
                    break;
                    
                case 5: // Quitter
                    InterfaceConsole.showMessage("Au revoir...\n");
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
     * @brief Fonction qui affiche le menu principale et demande à l'utilisateur
     * un choix parmis la liste.
     * @return Retourne le choix de l'utilisateur.
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
        InterfaceConsole.printMenu(menu);
        System.out.print("Choix : ");
        choice = InterfaceConsole.getInt(1, menu.length);
        
        return choice;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                 Function Static display *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui affiche un menu.
     * @param menu : Tableau de chaine de caractère à afficher.
     */
    public static void printMenu(String[] menu)
    {
        int count;
        
        count = 1;
        System.out.println();
        for( String str : menu)
        {
            InterfaceConsole.showMessage(count++ + " : " + str);
        }
    }
    
    /**
     * @brief Fonction qui affiche une chaine de caractère.
     * @param str : La chaine de caractère à afficher.
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
     * @brief Fonction qui vérifie si une chaine de carctère est un nombre.
     * @param coord : Chaine de carctère à vérifier.
     * @return Retourne si la chaine de caractère est bien un nombre ou non.
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
     * @brief Fonction qui demande à l'utilisateur une valeur entre deux
     * valeurs
     * @param valMin : Valeur minimale à saisir.
     * @param valMax : Valeur maximale à saisir.
     * @return Retourne la valeur saisi par l'utilisateur.
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
     * @brief Fonction qui demande à l'utilisateur de saisir une chaine
     * de caratcère.
     * @return Retourne la chaine de caractère saisie par l'utilisateur.
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
