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
public class playAction extends AbstractAction{
    
    private Fenetre fenetre;
    
    public playAction (Fenetre fenetre)
    {
        super("Jouer");
        this.fenetre = fenetre;
    }
    
    public static boolean isInteger(String input)
    {
       try
       { 
           Integer.parseInt(input); 
       }
       catch(NumberFormatException nfe) { return false; }
       
       return true;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String abs = this.fenetre.buttons.getAbs().getText();
        String ord = this.fenetre.buttons.getOrd().getText();
        if (!isInteger(abs) 
                || Integer.parseInt(abs) < 1 
                || Integer.parseInt(abs) > InterfaceSwing.getGame().getDeck().getSizeDeck()
                || !isInteger(ord)
                || Integer.parseInt(ord) < 1 
                || Integer.parseInt(ord) > InterfaceSwing.getGame().getDeck().getSizeDeck())
        {
            JOptionPane.showMessageDialog(this.fenetre.panel, "Coordonnées invalides, veuillez recommencer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            this.fenetre.panel = new Deck(InterfaceSwing.getGame().getDeck().getSizeDeck());
            this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
            this.fenetre.setContentPane(this.fenetre.panel);
            this.fenetre.setVisible(true);
        }
        else
        {
            Coordinates coordPlayed = new Coordinates (Integer.parseInt(abs), Integer.parseInt(ord));
            Move move = new Move(InterfaceSwing.getGame().getPlayerCurrent(), coordPlayed);
            if (!InterfaceSwing.getGame().playMove(move))
            {
                JOptionPane.showMessageDialog(this.fenetre.panel, "Coordonnées invalide, veuillez recommencer.", "ERROR", JOptionPane.ERROR_MESSAGE);
                this.fenetre.panel = new Deck(InterfaceSwing.getGame().getDeck().getSizeDeck());
                this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
                this.fenetre.setContentPane(this.fenetre.panel);
                this.fenetre.setVisible(true);
            }
            else
            {
                if (InterfaceJavaC.hasWinner() == 1)
                {
                    InterfaceSwing.getGame().getPlayerCurrent().win();
                    InterfaceSwing.getGame().getPlayer2().loose();
                    this.fenetre.panel = new Deck(InterfaceSwing.getGame().getDeck().getSizeDeck());
                    this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
                    this.fenetre.setContentPane(this.fenetre.panel);
                    this.fenetre.setVisible(true);
                    JOptionPane.showMessageDialog(null, InterfaceSwing.getGame().getPlayerCurrent().getPseudo() + " a gagné !");
                    InterfaceSwing.getGame().getDeck().deleteDeckC();
                    this.fenetre.panel = new Deck(0);
                    this.fenetre.panel.add(this.fenetre.buttons.menu("acceuil"));
                    this.fenetre.setContentPane(this.fenetre.panel);
                    this.fenetre.setVisible(true);
                }
                else
                {
                    Player temp = InterfaceSwing.getGame().getPlayer2();
                    InterfaceSwing.getGame().setPlayer2(InterfaceSwing.getGame().getPlayerCurrent());
                    InterfaceSwing.getGame().setPlayerCurrent(temp);
                    this.fenetre.panel = new Deck(InterfaceSwing.getGame().getDeck().getSizeDeck());
                    this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
                    this.fenetre.setContentPane(this.fenetre.panel);
                    this.fenetre.setVisible(true);
                }
            }
        }
    }
}
