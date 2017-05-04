/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

/**
 *
 * @author Eléana
 */
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
    
    public void save()
    {
        /* sauvegarde dans le fichier */
    }
    
}
