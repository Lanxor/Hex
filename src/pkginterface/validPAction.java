/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Eléana
 */
public class validPAction extends AbstractAction{
    
    private Game game;
    private Fenetre fenetre;
    private String source;
    
    public validPAction (Fenetre fenetre, Game game, String source)
    {
        super("Valider");
        this.fenetre = fenetre;
        this.game = game;
        this.source = source;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String pseudo = this.fenetre.buttons.getPseudo().getText();
        String mail = this.fenetre.buttons.getMail().getText();
        String yearOfBirth = (String)this.fenetre.buttons.getYear().getSelectedItem();
        Player newPlayer = new Player('b', pseudo, mail, Integer.parseInt(yearOfBirth));
        Player.add(newPlayer);
        if (source.equals("newH")){
            this.fenetre.panel = new Deck(0);
            this.fenetre.panel.add(this.fenetre.buttons.menu("crea partie"));
            this.fenetre.setContentPane(this.fenetre.panel);
            this.fenetre.setVisible(true);
        }
        if (source.equals("players")){
            this.fenetre.panel = new Deck(0);
            this.fenetre.panel.add(new JLabel("Joueurs Enregistrés"));
            JTextArea playersInfo = new JTextArea("");
            if (Player.getNumberOfPlayers() != 0){
                Player[] players = Player.getAllPlayers();
                for (int numPlayer = 0; numPlayer < Player.getNumberOfPlayers(); ++numPlayer)
                {
                    playersInfo = new JTextArea(playersInfo + "\n"
                        + Integer.toString(numPlayer +  1)
                        + " : pseudo : " + players[numPlayer].getPseudo()
                        + "mail : " + players[numPlayer].getMail());
                }
            }
            this.fenetre.panel.add(playersInfo);
            this.fenetre.panel.add(this.fenetre.buttons.menu(""));
            this.fenetre.setContentPane(this.fenetre.panel);
            this.fenetre.setVisible(true);
        }
    }
    
}
