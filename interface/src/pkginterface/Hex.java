/**
 * @file Hex.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Interface principale
 */
package pkginterface;

public class Hex {
    
    private static Menu menu;
    private static Deck tablier;
    private int nbJoueurs = 2;
    private static Player[] Joueurs;
    private static Player Joueur1;
    private static Player Joueur2;
    
    public static void init()
    {
        if (Joueurs.length != 0)
        {
            System.out.println("Choisir des joueurs : ");
            for (int cpt = 0; cpt < Joueurs.length; ++cpt){
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args)
    {      
        menu = new Menu();
        int resp = menu.Home();
        if (resp == 1){
            
            tablier = new Deck();
            tablier.init();
            System.out.println("Joueur 1");
            Joueur1 = new Player(tablier);
            Joueur1.init();
            System.out.println("Joueur 2");
            Joueur2 = new Player(tablier);
            Joueur2.init();
            tablier.displayBoard();
        }
        
        Joueur1.placeStone();
        tablier.displayBoard();
        Joueur2.placeStone();
        tablier.displayBoard();
    }
    
}