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
    private static Player Joueur1;
    private static Player Joueur2;
    
    public static void main(String[] args)
    {      
        menu = new Menu();
        menu.displayHome();
        int resp = menu.choiceHome();
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