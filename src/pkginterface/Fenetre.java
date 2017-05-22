/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Eléana
 */
public class Fenetre extends JFrame
{
    public JPanel panel;
    private Game game;
    public Buttons buttons = new Buttons(this, game);
    
    /**
     * @brief Constructeur.
     */
    public Fenetre(Game game)
    {        
        build();
        this.game = game;
    }

    /**
     * @brief Fonction qui construit la fenêtre du jeu.
     */
    private void build()
    {
        this.setTitle("Hex");
        this.setSize(800, 600);
        /* positione au centre */
        this.setLocationRelativeTo(null);
        /* termine processus avec la croix */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.panel = new Deck(0);
        this.panel.add(this.buttons.menu("acceuil"));
        
        setContentPane(this.panel);
        this.setVisible(true);
    }
    
}
