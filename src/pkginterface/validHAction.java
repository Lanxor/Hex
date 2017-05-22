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

/**
 *
 * @author Eléana
 */
public class validHAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public static boolean isInteger(String input)
    {
       try
       { 
           Integer.parseInt(input); 
       }
       catch(NumberFormatException nfe) { return false; }
       
       return true;
    }
    
    public validHAction (Fenetre fenetre)
    {
        super("Valider");
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String size = this.fenetre.buttons.getSizeD().getText();
        String player1 = (String)this.fenetre.buttons.getPlayersList1().getSelectedItem();
        String player2 = (String)this.fenetre.buttons.getPlayersList2().getSelectedItem();
        if (!isInteger(size) 
                || Integer.parseInt(size) < 2 
                || Integer.parseInt(size) > 19
                || player1.equals("Joueur 1")
                || player2.equals("Joueur 2")
                || (!player1.equals("Nouveau Joueur") && player1.equals(player2)))
        {
            JOptionPane.showMessageDialog(this.fenetre.panel, "Erreur, veuillez recommencer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            this.fenetre.panel = new Deck(0);
            this.fenetre.panel.add(this.fenetre.buttons.menu("crea partie"));
            this.fenetre.setContentPane(this.fenetre.panel);
            this.fenetre.setVisible(true);
        }
        else
        {
            if (player1.equals("Nouveau Joueur") || player2.equals("Nouveau Joueur"))
            {
                this.fenetre.panel = new Deck(0);
                this.fenetre.panel.add(this.fenetre.buttons.menu("crea joueur 1"));
                this.fenetre.setContentPane(this.fenetre.panel);
                this.fenetre.setVisible(true);
            }
            else
            {
                Player p1 = Player.load(Player.getPathFilePlayer(player1)+".player");
                Player p2 = Player.load(Player.getPathFilePlayer(player2)+".player");
                InterfaceSwing.getGame().getDeck().setSize(Integer.parseInt(size));
                InterfaceSwing.getGame().setPlayerCurrent(p1);
                InterfaceSwing.getGame().setPlayer2(p2);
                InterfaceSwing.getGame().getPlayerCurrent().setColor('b');
                InterfaceSwing.getGame().getPlayer2().setColor('w');
                InterfaceSwing.getGame().getDeck().createDeckC();
                this.fenetre.panel = new Deck(InterfaceSwing.getGame().getDeck().getSizeDeck());
                this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
                this.fenetre.setContentPane(this.fenetre.panel);
                this.fenetre.setVisible(true);
            }
        }
    }
}
