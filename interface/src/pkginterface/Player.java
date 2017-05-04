/**
 * @file Player.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des joueurs
 */
package pkginterface;

import java.util.Scanner;

public class Player {
    
    private String pseudo;
    private String color;
    private String mail;
    private int yearOfBirth;
    private Deck deck;
    
    public Player(Deck deck)
    {
        this.deck = deck;
    }
    public String getPseudo()
    {
        return this.pseudo;
    }
    
    public String getColor()
    {
        return this.color;
    }
    
    public void init()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("pseudo : ");
        this.pseudo = keyboard.next();
        System.out.print("couleur : ");
        this.color = keyboard.next();
    }
    
    public void placeStone()
    {
        System.out.println(this.pseudo + " à toi !");
        Coordinates coordinates = new Coordinates(this.deck);
        coordinates.setCoord();
        this.deck
                .cell[coordinates.getAbscisse()][coordinates.getOrdonnee()]
                .placeStone(this.color);
    }
    
}