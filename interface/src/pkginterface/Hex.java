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
    
    /* C'EST DEGUEULASSE NE REGARDEZ PAS */
    
    private Menu menu;
    private Deck tablier;
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
    
    public int getInt(int valMax)
    {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.next();
        while (!isInteger(input) 
                || Integer.parseInt(input) < 0 
                || Integer.parseInt(input) >= valMax){
            System.out.print("Vous devez entrer un entier entre 0 et " 
                    + Integer.toString(valMax)
                    + ", veuillez recommencer : ");
            input = keyboard.next();
        }
        return Integer.parseInt(input);
    }
    
    public void playMove()
    {
        System.out.print("abs : ");
        this.abscisse = getInt(this.tablier.getSize());
        System.out.print("ord : ");
        this.ordonnee = getInt(this.tablier.getSize());
        
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
                .vertice[coordinates.getAbscisse()][coordinates.getOrdonnee()]
                .placeStone(this.color);
    }
    
    public void Home()
    {
        System.out.println("1 : Créer une nouvelle partie\n"
                + "2 : Charger une partie\n"
                + "3 : Quitter"
                + "4 : Scores");
        Scanner keyboard = new Scanner(System.in);
        String choice = keyboard.next();
        while (!isInteger(choice) 
                || Integer.parseInt(choice) < 0 
                || Integer.parseInt(choice) > 4){
            System.out.print("erreur ");
            choice = keyboard.next();
        }
        return Integer.parseInt(choice);
    }
    
    public int Choice()
    {
        System.out.println("1 : Jouer\n"
                + "2 : Revenir au coup précédent"
                + "3 : Sauvegarder\n"
                + "4 : Sauvegarder et Quitter"
                + "5 : Quitter sans sauvegarder");
        Scanner keyboard = new Scanner(System.in);
        String choice = keyboard.next();
        while (!isInteger(choice) 
                || Integer.parseInt(choice) < 0 
                || Integer.parseInt(choice) > 5){
            System.out.print("erreur ");
            choice = keyboard.next();
        }
        return Integer.parseInt(choice);
    }

    public void saveFile(String nameFile)
    {
        String str = "";
        File f = new File("testFile.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            str = "\\hex\n";
            str += "\\dim" + this.size + "\n";
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