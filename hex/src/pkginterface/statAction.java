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
public class statAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public statAction (Fenetre fenetre)
    {
        super("Statistiques");
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.fenetre.panel = new Deck(0);
        Box scores = Box.createVerticalBox();
        scores.add(new JLabel("Statistiques"));
        if (Player.getNumberOfPlayers() != 0){
            Player[] players = Player.getAllPlayers();
            for (int numPlayer = 0; numPlayer < Player.getNumberOfPlayers(); ++numPlayer)
            {
                scores.add(new JTextArea(players[numPlayer].getPseudo() 
                        + players[numPlayer].getScore().toString() 
                        + "\n"));
            }
        }
        scores.add(this.fenetre.buttons.menu(""));
        this.fenetre.panel.add(scores);
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
