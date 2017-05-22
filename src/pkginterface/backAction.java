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
public class backAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public backAction (Fenetre fenetre)
    {
        super("Revenir en arrière");
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        InterfaceSwing.getGame().goBackN(1);
        Player temp = InterfaceSwing.getGame().getPlayer2();
        InterfaceSwing.getGame().setPlayer2(InterfaceSwing.getGame().getPlayerCurrent());
        InterfaceSwing.getGame().setPlayerCurrent(temp);
        this.fenetre.panel = new Deck(InterfaceSwing.getGame().getDeck().getSizeDeck());
        this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
