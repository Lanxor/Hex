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
    
    public Player(String pseudo, String color, String mail, int yearOfBirth)
    {
        this.pseudo = pseudo;
        this.color = color;
        this.mail = mail;
        this.yearOfBirth = yearOfBirth;
    }
    public String getPseudo()
    {
        return this.pseudo;
    }
    
    public String getColor()
    {
        return this.color;
    }
    
    public void save(){
        /* sauvegarde dans le fichier */
    }
    
}