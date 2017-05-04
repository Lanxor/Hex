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
                + "3 : Statistiques\n"
                + "4 : Quitter");
    }
    
    public void Choice(int round)
    {
        if (round == 1)
            System.out.println("1 : Jouer\n"
                + "2 : Sauvegarder\n"
                + "3 : Sauvegarder et Quitter\n"
                + "4 : Quitter sans sauvegarder");
        else
            if (round == 2)
                System.out.println("1 : Jouer\n"
                    + "2 : Echanger les couleurs\n"
                    + "3 : Sauvegarder\n"
                    + "4 : Sauvegarder et Quitter\n"
                    + "5 : Quitter sans sauvegarder");
            else
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