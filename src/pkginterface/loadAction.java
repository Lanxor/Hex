/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Eléana
 */
public class loadAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public loadAction (Fenetre fenetre)
    {
        super("Charger une Partie");
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.fenetre.panel = new Deck(0);
        Box saveguard = Box.createVerticalBox();
        saveguard.add(new JLabel("Sauvegardes"));
        if (Saveguard.getNumberOfSaveguard() != 0)
        {
            String[] saveguards = Saveguard.getListSaveguard();
            for (int numSG = 1; numSG <= Saveguard.getNumberOfSaveguard(); ++numSG)
            {
                saveguard.add(new JTextArea(Integer.toString(numSG)
                        + " : "
                        + saveguards[numSG-1]
                        + "\n"));
            }
        }
        this.fenetre.panel.add(saveguard);
        this.fenetre.panel.add(this.fenetre.buttons.menu("saveguard"));
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
