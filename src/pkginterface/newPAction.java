/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Eléana
 */
public class newPAction extends AbstractAction{
    
    private Fenetre fenetre;
    private int type;
    private Game game;
    
    public newPAction (Fenetre fenetre, int type, Game game)
    {
        super("Création Joueur");
        this.fenetre = fenetre;
        this.type = type;
        this.game = game;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.fenetre.panel = new Deck(0);
        if (type == 1)
            this.fenetre.panel.add(this.fenetre.buttons.menu("crea joueur 1", this.game));
        else
            this.fenetre.panel.add(this.fenetre.buttons.menu("crea joueur 2", this.game));
        
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
