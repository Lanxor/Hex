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
public class saveAction extends AbstractAction{
    
    private Game game;
    
    public saveAction (Game game)
    {
        super("Sauvegarder");
        this.game = game;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.game.save();
        JOptionPane.showMessageDialog(null, "Sauvegarde Effectuée");
    }
}
