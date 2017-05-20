/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Eléana
 */
public class Buttons extends JPanel{
    
    private Fenetre fenetre;
    private Game game;
    
    private Box bouton;
    private JComboBox playersList1;
    private JComboBox playersList2;
    private JComboBox numPlayersList;
    private JComboBox numSaveguardsList;
    private JTextField size;
    private JTextField pseudo;
    private JTextField mail;
    private JComboBox yearOfBirth;
    private JTextField ord;
    private JTextField abs;
    private Box move;
    private Box inGame;
    
    public Buttons(Fenetre fenetre, Game game)
    {
        this.fenetre = fenetre;
        this.game = game;
    }
    
    public Box menu(String choice)
    {
        
        JButton button_newH = new JButton(new newHAction(this.fenetre));
        JButton button_load = new JButton(new loadAction(this.fenetre));
        JButton button_stat = new JButton(new statAction(this.fenetre));
        JButton button_players = new JButton(new playersAction(this.fenetre));
        JButton button_quit = new JButton(new quitAction(this.fenetre));
        JButton button_save = new JButton(new saveAction(this.game));
        JButton button_saveq = new JButton(new saveqAction(this.fenetre, this.game));
        JButton button_play = new JButton(new playAction(this.fenetre, this.game));
        JButton button_acceuil = new JButton(new acceuilAction(this.fenetre, "Acceuil"));
        JButton button_quitH = new JButton(new acceuilAction(this.fenetre, "Quitter"));
        JButton button_ok = new JButton(new validHAction(this.fenetre, this.game));
        JButton button_validH = new JButton(new validHAction(this.fenetre, this.game));
        JButton button_back = new JButton(new backAction(this.fenetre, this.game));
        JButton button_newP = new JButton(new newPAction(this.fenetre, 2));
        JButton button_deleteP = new JButton(new deletePAction(this.fenetre));
        JButton button_loadS = new JButton(new loadSAction(this.fenetre, this.game));
        
        JTextArea rules = new JTextArea("\nRègles : Les joueurs jouent chacun leur tour. "
                + "À chaque tour, le joueur place un pion \nde sa couleur sur une"
                + " case libre du plateau. Le premier joueur qui réussit à \n"
                + "relier ses deux bords par un chemin de pions contigus de sa "
                + "couleur a gagné. \nIl ne peut y avoir qu'un pion par case. "
                + "Les pions posés le sont définitivement, \nils ne peuvent être "
                + "ni retirés ni déplacés.");
        
        String[] years;
        
        switch (choice)
        {
            case "acceuil" :
                bouton = Box.createVerticalBox();
                bouton.add(button_newH);
                bouton.add(button_load);
                bouton.add(button_stat);
                bouton.add(button_players);
                bouton.add(button_quit);
                bouton.add(rules);
                return bouton;
            case "crea partie" :
                bouton = Box.createVerticalBox();
                String[] players1 = new String[Player.getNumberOfPlayers()+2];
                players1[0] = "Joueur 1";
                String[] players2 = new String[Player.getNumberOfPlayers()+2];
                players2[0] = "Joueur 2";
                if (Player.getNumberOfPlayers() != 0)
                {
                    Player[] players = Player.getAllPlayers();
                    for (int numPlayer = 0; numPlayer < Player.getNumberOfPlayers(); ++numPlayer)
                    {
                        players1[numPlayer+1] = players[numPlayer].getPseudo();
                        players2[numPlayer+1] = players[numPlayer].getPseudo();
                    }
                }
                players1[Player.getNumberOfPlayers()+1] = "Nouveau Joueur";
                playersList1 = new JComboBox(players1);
                players2[Player.getNumberOfPlayers()+1] = "Nouveau Joueur";
                playersList2 = new JComboBox(players2);
                bouton.add(playersList1);
                bouton.add(playersList2);
                bouton.add(new JLabel("Taille du Tablier [" 
                        + Deck.getMinSize()
                        + "-"
                        + Deck.getMaxSize()
                        +"] :"));
                size = new JTextField();
                bouton.add(size);
                bouton.add(button_ok);
                bouton.add(button_acceuil);
                return bouton;
            case "jeu" :
                bouton = Box.createVerticalBox();
                bouton.add(button_save);
                bouton.add(button_saveq);
                bouton.add(button_quitH);
                move = Box.createVerticalBox();
                move.add(new JLabel("ordonnée :"));
                ord = new JTextField();
                move.add(ord);
                move.add(new JLabel("abscisse :"));
                abs = new JTextField();
                move.add(abs);
                move.add(button_play);
                inGame = Box.createHorizontalBox();
                inGame.add(bouton);
                inGame.add(new JLabel(this.game.getPlayerCurrent().getPseudo()
                        + ", c'est à toi !"));
                inGame.add(move);
                inGame.add(button_back);
                return inGame;
            case "crea joueur 1" :
                bouton = Box.createVerticalBox();
                bouton.add(new JLabel("Pseudo :"));
                pseudo = new JTextField();
                bouton.add(pseudo);
                bouton.add(new JLabel("Adresse e-Mail :"));
                mail = new JTextField();
                bouton.add(mail);
                bouton.add(new JLabel("Année de Naissance :"));
                years = new String[100];
                for (int i=0; i<100; ++i)
                {
                    years[i]=Integer.toString(i+1917);
                }
                yearOfBirth = new JComboBox(years);
                bouton.add(yearOfBirth);
                bouton.add(new JButton(new validPAction(this.fenetre, this.game, "newH")));
                bouton.add(button_acceuil);
                return bouton;
            case "joueurs" :
                bouton = Box.createVerticalBox();
                bouton.add(button_newP);
                if (Player.getNumberOfPlayers() != 0)
                {
                    String[] numPlayers = new String[Player.getNumberOfPlayers()];
                    for (int numPlayer = 0; numPlayer < Player.getNumberOfPlayers(); ++numPlayer)
                    {

                        numPlayers[numPlayer] = Integer.toString(numPlayer + 1);
                    }
                    numPlayersList = new JComboBox(numPlayers);
                }
                bouton.add(numPlayersList);
                bouton.add(button_deleteP);
                bouton.add(button_acceuil);
                return bouton;
            case "crea joueur 2" :
                bouton = Box.createVerticalBox();
                bouton.add(new JLabel("Pseudo :"));
                pseudo = new JTextField();
                bouton.add(pseudo);
                bouton.add(new JLabel("Adresse e-Mail :"));
                mail = new JTextField();
                bouton.add(mail);
                bouton.add(new JLabel("Année de Naissance :"));
                years = new String[100];
                for (int i=0; i<100; ++i)
                {
                    years[i]=Integer.toString(i+1917);
                }
                yearOfBirth = new JComboBox(years);
                bouton.add(yearOfBirth);
                bouton.add(new JButton(new validPAction(this.fenetre, this.game, "players")));
                bouton.add(button_acceuil);
                return bouton;
            case "saveguard" :
                bouton = Box.createVerticalBox();
                if (Saveguard.getNumberOfSaveguard() != 0)
                {
                    String[] numSaveguards = new String[Saveguard.getNumberOfSaveguard()];
                    for (int numSG = 0; numSG < Saveguard.getNumberOfSaveguard(); ++numSG)
                    {
                        numSaveguards[numSG] = Integer.toString(numSG);
                    }
                    numSaveguardsList = new JComboBox(numSaveguards);
                    bouton.add(numSaveguardsList);
                    bouton.add(button_loadS);
                }
                else
                {
                    bouton.add(new JLabel("aucune partie enregistrée"));
                }
                bouton.add(button_acceuil);
                return bouton;
            default :
                bouton = Box.createVerticalBox();
                bouton.add(button_acceuil);
                return bouton;
        }
    }
    
    public JComboBox getPlayersList1()
    {
        return playersList1;
    }
    
    public JComboBox getPlayersList2()
    {
        return playersList2;
    }
    
    public JComboBox getNumPlayersList()
    {
        return numPlayersList;
    }
    
    public JComboBox getNumSaveguardList()
    {
        return numSaveguardsList;
    }
    
    public JTextField getSizeD()
    {
        return size;
    }
    
    public JTextField getAbs()
    {
        return abs;
    }
    
    public JTextField getOrd()
    {
        return ord;
    }
    
    public JTextField getPseudo()
    {
        return pseudo;
    }
    
    public JTextField getMail()
    {
        return mail;
    }
    
    public JComboBox getYear(){
        return yearOfBirth;
    }
}
