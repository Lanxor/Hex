/**
 * @file Menu.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des menu
 */
package pkginterface;

import java.util.Scanner;

public class Menu {
    
    public void displayHome()
    {
        System.out.println("1 : Créer une nouvelle partie\n"
                + "2 : Charger une partie\n"
                + "3 : Quitter"
                + "4 : Scores");
    }
    
    public int choiceHome()
    {
        Scanner keyboard = new Scanner(System.in);
        int choice = Integer.parseInt(keyboard.next());
        switch (choice) {
            case 1: 
                return 1;
            default: 
                return 0;
        }
    }
}