/**
 * @file Player.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des joueurs
 */

package pkginterface;

public class Player {
    
    private String pseudo;
    private char color;
    private String mail;
    private int yearOfBirth;
    
    public Player(String pseudo, char color, String mail, int yearOfBirth)
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
    
    public char getColor()
    {
        return this.color;
    }
}