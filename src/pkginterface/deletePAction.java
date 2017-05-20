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
public class deletePAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public deletePAction (Fenetre fenetre)
    {
        super("Supprimer");
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String player = (String)this.fenetre.buttons.getNumPlayersList().getSelectedItem();
        Player.delete(Integer.parseInt(player));
        JOptionPane.showMessageDialog(null, "Joueur Supprimé.");
        this.fenetre.panel = new Deck(0);
        Box playersList = Box.createVerticalBox();
        playersList.add(new JLabel("Joueurs Enregistrés"));
        if (Player.getNumberOfPlayers() != 0)
        {
            Player[] players = Player.getAllPlayers();
            for (int numPlayer = 0; numPlayer < Player.getNumberOfPlayers(); ++numPlayer)
            {
                playersList.add(new JTextArea(Integer.toString(numPlayer +  1)
                        + " : pseudo : " + players[numPlayer].getPseudo()
                        + "\n"
                        + "mail : " + players[numPlayer].getMail()
                        + "\n"));
            }
        }
        playersList.add(this.fenetre.buttons.menu("joueurs"));
        this.fenetre.panel.add(playersList);
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
