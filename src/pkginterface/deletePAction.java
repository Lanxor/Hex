/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
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
        this.fenetre.panel.add(new JLabel("Joueurs Enregistrés"));
        JTextArea playersInfo = new JTextArea("");
        Player[] players = Player.getAllPlayers();
        for (int numPlayer = 0; numPlayer < Player.getNumberOfPlayers(); ++numPlayer)
        {
            playersInfo = new JTextArea(playersInfo + "\n"
                + "pseudo : " + players[numPlayer].getPseudo()
                + "mail : " + players[numPlayer].getMail());
        }
        this.fenetre.panel.add(playersInfo);
        this.fenetre.panel.add(this.fenetre.buttons.menu(""));
        this.fenetre.setContentPane(this.fenetre.panel);
        this.fenetre.setVisible(true);
    }
}
