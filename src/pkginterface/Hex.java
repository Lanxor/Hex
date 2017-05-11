/**
 * @file Hex.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Interface principale
 */

package pkginterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hex {
    
    private Menu menu;
    private Historic historic;
    private Deck deck;
    private Player Player1;
    private Player Player2;
    private Player[] Players;
    
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
    
    public int getInt(int valMin, int valMax)
    {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.next();
        while (!isInteger(input) 
                || Integer.parseInt(input) < valMin 
                || Integer.parseInt(input) > valMax){
            System.out.print("Vous devez entrer un entier entre "
                    + Integer.toString(valMin)
                    + " et "
                    + Integer.toString(valMax)
                    + ", veuillez recommencer : ");
            input = keyboard.next();
        }
        return Integer.parseInt(input);
    }
    
    public void initPlayer(int numPlayer)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Joueur " + Integer.toString(numPlayer));
        int choice;
        System.out.println("1 : Nouveau Joueur");
        if (this.Players != null){
            for (int cpt = 0; cpt < this.Players.length; ++cpt){
                System.out.println(Integer.toString(cpt + 2) 
                        + " : " + this.Players[cpt].getPseudo());
            }
            choice = getInt(1, this.Players.length + 2);
        }
        choice = getInt(1, 1);
        if (choice == 1)
        {
            System.out.print("pseudo : ");
            String pseudo = keyboard.next();
            System.out.print("mail : ");
            String mail = keyboard.next();
            if (numPlayer == 1)
                this.Player1 = new Player(pseudo, '*', mail);
            else
                this.Player2 = new Player(pseudo, 'o', mail);
        }
        else
        {
            if (numPlayer == 1)
                Player1 = Players[choice-2];
            else
                Player2 = Players[choice-2];
        }
    }
    
    public void initDeck(){
        System.out.println("De quelle taille voulez-vous votre tablier ? ");
        int t = getInt(5, 30);
        this.deck = new Deck(t);
    }
    
    public Coordinates getCoordinates()
    {
        System.out.println("Veuillez entrer des coordonnées.");
        System.out.print("abs : ");
        int abs = getInt(0, this.deck.getSize() - 1);
        System.out.print("ord : ");
        int ord = getInt(0, this.deck.getSize() - 1);
        Coordinates c = new Coordinates(abs, ord);
        return c;
    }
    
    public void placeStone(Coordinates coord)
    {
        /* envois les coordonnée a c */
    }
    
    public void play()
    {      
        menu = new Menu();
        menu.home();
        int resp = getInt(1, 4);
        if (resp == 1){
            this.initDeck();
            initPlayer(1);
            initPlayer(2);
        }
        this.placeStone(this.getCoordinates());
    }
    
    public void saveFile(String nameFile)
    {
        String str = "";
        File f = new File("testFile.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            str = "\\hex\n";
            str += "\\dim" + this.deck.getSize() + "\n";
            str += this.toString();
            fw.write(str);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
