/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author Eléana
 */
public class saveqAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public saveqAction (Fenetre fenetre)
    {
        super("Sauvegarder et Quitter");
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        InterfaceSwing.getGame().save();
        JOptionPane.showMessageDialog(null, "Sauvegarde Effectuée");
        this.fenetre.panel = new Deck(0);
        this.fenetre.panel.add(this.fenetre.buttons.menu("acceuil"));
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
