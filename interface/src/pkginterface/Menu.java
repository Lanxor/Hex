/**
 * @file Menu.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des menu
 */
package pkginterface;

import java.util.Scanner;

public class Menu {
    
    public void Home()
    {
        System.out.println("1 : Créer une nouvelle partie\n"
                + "2 : Charger une partie\n"
                + "3 : Quitter\n"
                + "4 : Statistiques");
    }
    
    public void Choice()
    {
        System.out.println("1 : Jouer\n"
                + "2 : Revenir au coup précédent\n"
                + "3 : Sauvegarder\n"
                + "4 : Sauvegarder et Quitter\n"
                + "5 : Quitter sans sauvegarder");
    }
    
    public void Statistics()
    {
        /* récupère les joueurs dans le fichier et affiche leurs stats */
    }
}