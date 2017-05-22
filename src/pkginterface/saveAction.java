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
    
    
    public saveAction ()
    {
        super("Sauvegarder");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        InterfaceSwing.getGame().save();
        JOptionPane.showMessageDialog(null, "Sauvegarde Effectuée");
    }
}
