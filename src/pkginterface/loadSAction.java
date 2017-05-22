/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Eléana
 */
public class loadSAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public loadSAction (Fenetre fenetre)
    {
        super("Chargement");
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String gameL = (String)this.fenetre.buttons.getNumSaveguardList().getSelectedItem();
        Saveguard.loadSaveguard(InterfaceSwing.getGame(), Integer.parseInt(gameL));
        this.fenetre.panel = new Deck(InterfaceSwing.getGame().getDeck().getSizeDeck());
        this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
