/**
 * @file Move.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion du coup
 */

package pkginterface;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Move {
    
    private Player player;
    private Coordinates coord;
    
    public Move(Player p, Coordinates c)
    {
        this.player = p;
        this.coord = c;
    }
    
    public void play ()
    {
        /* envoie info au C */
    }
    
    public boolean isValid()
    {
        /* recupere info du C */
    }
    
    public String toString()
    {
        return "\\play " + this.player.getColor() + " " + this.coord.getCoordonnee();
    }
    
}
