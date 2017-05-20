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
    private Game game;
    
    public playAction (Fenetre fenetre, Game game)
    {
        super("Jouer");
        this.fenetre = fenetre;
        this.game = game;
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
                || Integer.parseInt(abs) > this.game.getDeck().getSizeDeck()
                || !isInteger(ord)
                || Integer.parseInt(ord) < 1 
                || Integer.parseInt(ord) > this.game.getDeck().getSizeDeck())
        {
            JOptionPane.showMessageDialog(this.fenetre.panel, "Coordonnées invalides, veuillez recommencer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            this.fenetre.panel = new Deck(this.game.getDeck().getSizeDeck());
            this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
            this.fenetre.setContentPane(this.fenetre.panel);
            this.fenetre.setVisible(true);
        }
        else
        {
            Coordinates coordPlayed = new Coordinates (Integer.parseInt(abs), Integer.parseInt(ord));
            Move move = new Move(this.game.getPlayerCurrent(), coordPlayed);
            if (!move.isValid())
            {
                JOptionPane.showMessageDialog(this.fenetre.panel, "Coordonnées invalide, veuillez recommencer.", "ERROR", JOptionPane.ERROR_MESSAGE);
                this.fenetre.panel = new Deck(this.game.getDeck().getSizeDeck());
                this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
                this.fenetre.setContentPane(this.fenetre.panel);
                this.fenetre.setVisible(true);
            }
            else
            {
                move.play();
                Player temp = this.game.getPlayer2();
                this.game.setPlayer2(this.game.getPlayerCurrent());
                this.game.setPlayerCurrent(temp);
                if (InterfaceJavaC.hasWinner() == 1)
                {
                    char colorWinner = InterfaceJavaC.getWinner();
                    Player winner;
                    if (this.game.getPlayerCurrent().getColor() == colorWinner)
                        winner = this.game.getPlayerCurrent();
                    else
                        winner = this.game.getPlayer2();
                    JOptionPane.showMessageDialog(null, winner.getPseudo() + " a gagné !");
                    this.game.endGame();
                    this.fenetre.panel = new Deck(0);
                    this.fenetre.panel.add(this.fenetre.buttons.menu("acceuil"));
                    this.fenetre.setContentPane(this.fenetre.panel);
                    this.fenetre.setVisible(true);
                }
                else
                {
                    this.fenetre.panel = new Deck(this.game.getDeck().getSizeDeck());
                    this.fenetre.panel.add(this.fenetre.buttons.menu("jeu"));
                    this.fenetre.setContentPane(this.fenetre.panel);
                    this.fenetre.setVisible(true);
                }
            }
        }
    }
}
