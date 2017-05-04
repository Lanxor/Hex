/**
 * @file Hex.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Interface principale
 */
package pkginterface;

import java.util.Scanner;

public class Hex {
    
    private Menu menu;
    private Deck tablier;
    private Player[] Players;
    private Player Player1;
    private Player Player2;
    
    private boolean isInteger(String coord) {
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
    
    public void init(int numJoueur)
    {
        if (Players.length != 0)
        {
            System.out.println("Choisir des joueurs : ");
            System.out.println("Joueur " + Integer.toString(numJoueur));
            System.out.println("1 : Nouveau Joueur");
            for (int cpt = 0; cpt < Players.length; ++cpt){
                System.out.println(Integer.toString(cpt+2) 
                        + " : " + Players[cpt].getPseudo());
            }
            Scanner keyboard = new Scanner(System.in);
            String choice = keyboard.next();
            while (!isInteger(choice) 
                || Integer.parseInt(choice) < 0 
                || Integer.parseInt(choice) > 5){
            System.out.print("erreur ");
            choice = keyboard.next();
            }
            if (Integer.parseInt(choice) == 1)
            {
                if (numJoueur == 1)
                {
                    Player1 = new Player(tablier);
                    Player1.init();
                }
                else
                {
                    Player2 = new Player(tablier);
                    Player2.init();
                }
            }
            else
            {
                if (numJoueur == 1)
                    Player1 = Players[Integer.parseInt(choice)-2];
                else
                    Player2 = Players[Integer.parseInt(choice)-2];
            }
        }
        else
        {
            System.out.println("Joueur 1");
            Player1 = new Player(tablier);
            Player1.init();
            System.out.println("Joueur 2");
            Player2 = new Player(tablier);
            Player2.init();
        }
    }
    
    public void play()
    {      
        menu = new Menu();
        int resp = menu.Home();
        if (resp == 1){
            
            tablier = new Deck();
            tablier.init();
            init(1);
            init(2);
            tablier.displayBoard();
        }
        
        Player1.placeStone();
        tablier.displayBoard();
        Player2.placeStone();
        tablier.displayBoard();
    }
    
}