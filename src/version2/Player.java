package version2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable {
    
    private char color;
    private String pseudo;
    private String mail;
    private int yearOfBirth;
    
    public Player(char color)
    {
        this.color = color;
    }
    
    public Player(char color, String pseudo)
    {
        this.color = color;
        this.pseudo = pseudo;
    }
    
    public Player(char color, String pseudo, String mail)
    {
        this.color = color;
        this.pseudo = pseudo;
        this.mail = mail;
    }
    
    public Player(char color, String pseudo, String mail, int yearOfBirth)
    {
        this.color = color;
        this.pseudo = pseudo;
        this.mail = mail;
        this.yearOfBirth = yearOfBirth;
    }
    
    /**
     * @brief Affiche le menu
     * @return 
     */
    public static int menuPlayer()
    {
        String[] menu;
        int choice;
        
        menu = new String[6];
        menu[0] = "Profile\n";
        menu[1] = "Changer de couleur du personnage\n";
        menu[2] = "Changer de personnage\n";
        menu[3] = "Ajouter un personnage\n";
        menu[4] = "Supprimer un personnage\n";
        menu[5] = "Retour\n";
        Interface.printMenu(menu);
        Interface.showMessage("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        return choice;
    }
    
    public static void index(Game game)
    {
        boolean leave;
        
        leave = false;
        while ( !leave )
        {
            switch ( Player.menuPlayer() )
            {
                case 1: // Profile
                    Interface.showMessage("\nVoici votre profil : \n");
                    Player.showProfilPlayer(game);
                    break;
                case 2: // Changer de couleur de joueur
                    Player.changeColorPlayer(game);
                    Interface.showMessage("\nVous venez de changer de couleur.\n");
                    break;
                case 3: // Changer de joueur
                    Player.changePlayer(game);
                    Interface.showMessage("\nVous venez de changer de joueur.\n");
                    break;
                case 4: // Ajouter un joueur
                    Player.addPlayer();
                    Interface.showMessage("\nVous venez d'ajouter un joueur.\n");
                    break;
                case 5: // Supprimer un joueur
                    Player.deletePlayer(game);
                    Interface.showMessage("\nVous venez de supprimer un joueur.\n");
                    break;
                case 6: // Retour
                    leave = true;
                    break;
            }
        }
        
    }
    
    public static void showProfilPlayer(Game game)
    {
        String str;
        String color;
        Player player;
        
        player = game.getPlayerCurrent();
        color = null;
        str = "";
        if ( player.getColor() == 'w' )
            color = "Blanc";
        else if ( player.getColor() == 'b' )
            color = "Noir";
        str += "-----------------------------\n";
        str += "-----------------------------\n";
        str += "Profile de " + player.getPseudo() + "\n"
                + "Couleur : " + color + "\n"
                + "Adresse mail : " + player.getMail() + "\n"
                + "Année de naissance : " + player.getYearOfBirth() + "\n";
        str += "-----------------------------\n";
        str += "-----------------------------\n";
        
        Interface.showMessage(str);
    }
    
    public static void changeColorPlayer(Game game)
    {
        char color;
        
        color = game.getPlayerCurrent().getColor();
        
        if ( color == 'b' )
            color = 'w';
        else if ( color == 'w' )
            color = 'b';
        game.getPlayerCurrent().setColor(color);
    }
    
    /**
     * @brief Change de joueur parmis la liste proposé
     * @param game 
     */
    public static void changePlayer(Game game)
    {
        Player player;
        int numberPlayer, choice;
        
        Interface.showMessage(Player.listPlayer());
        Interface.showMessage("Choississez un joueur : ");
        numberPlayer = Player.getNumberOfPlayers();
        choice = Interface.getInt(1, numberPlayer);
        player = Player.load(choice);
        game.setPlayerCurrent(player);
    }
    
    public static Player addPlayer()
    {
        Player player;
        
        player = Player.askNew();
        Player.add(player);
        return player;
    }
    
    /**
     * Fonction qui demande à l'utilisateur qu'elle joueur supprimer
     */
    public static void deletePlayer(Game game)
    {
        Player player;
        int numberPlayer, choice;
        
        do {
            Interface.showMessage(Player.listPlayer());
            Interface.showMessage("Tapez 0 pour quitter.\n");
            Interface.showMessage("Choississez un joueur : ");
            numberPlayer = Player.getNumberOfPlayers();
            choice = Interface.getInt(1, numberPlayer);
            player = Player.load(choice);
            if ( game.getPlayerCurrent().equals(player) )
                Interface.showMessage("Vous ne pouvez vous selectionner.\n");
        } while ( game.getPlayerCurrent().equals(player) );
        Player.delete(choice);
        Interface.showMessage("Joueur supprimé.\n");
    }

    
    /**
     * @brief Affiche la liste des joueurs enregistrer.
     * @return 
     */
    public static String listPlayer()
    {
        String str;
        ObjectInputStream ois;
        int count;
        File f;
        Player player;
        
        str = "";
        count = 1;
        f = new File("../player/");
        for ( File file : f.listFiles())
        {
            try {
                try {
                    ois = new ObjectInputStream(
                                new BufferedInputStream(
                                    new FileInputStream(file)));
                    try {
                        player = (Player)ois.readObject();
                        str += count++ + " : " + player.getPseudo()
                                + " (" + player.getColor() + ")\n";
                        ois.close();
                    } catch (ClassNotFoundException e) {}
                } catch (FileNotFoundException e) {}
                catch (IOException e) {}
            } catch (NullPointerException e) {}
        }
        return str;
    }
    
    /**
     * @brief Demande à l'utilisateur les données d'un joueur.
     * @return 
     */
    public static Player askNew()
    {
        String colorStr;
        char color;
        String pseudo;
        String mail;
        int yearOfBirth;
        
        Interface.showMessage("Nouveau joueur.\n");
        Interface.showMessage("Saississez un pseudonyme : ");
        pseudo = Interface.getString();
        Interface.showMessage("Saississez votre email : ");
        mail = Interface.getString();
        Interface.showMessage("Saississez votre année de naissance : ");
        yearOfBirth = Interface.getInt(1950, 2017);
        color = 'w';
        do {
            Interface.showMessage("Quel couleur souhaitez vous ? (Blanc/Noir) : ");
            colorStr = Interface.getString();
        } while ( !"Blanc".equals(colorStr) && !"Noir".equals(colorStr) );
        
        switch ( colorStr )
        {
            case "Blanc":
                color = 'w';
                break;
            case "Noir":
                color = 'b';
                break;
        }
        
        return new Player(color, pseudo, mail, yearOfBirth);
    }
    
    /**
     * @brief Charge un joueur au lancement du jeu.
     * @param game
     * @return 
     */
    public static Game loadStartPlayer(Game game)
    {
        Player player;
        
        if ( Player.emptyPlayer() ) {
            player = Player.addPlayer();
            game.setPlayerCurrent(player);
        } else
            Player.changePlayer(game);
        
        return game;
    }
    
    /**
     * @brief Compte le nombre de joueurs enregistrer.
     * @return 
     */
    public static int getNumberOfPlayers()
    {
        File folder;
        
        folder = new File("../player/");
        return folder.listFiles().length;
    }
    
    /**
     * @brief Indique si la liste des joueurs enregistrer est vide.
     * @return 
     */
    public static boolean emptyPlayer()
    {
        return Player.getNumberOfPlayers() < 1;
    }
    
    public char getColor()
    {
        return this.color;
    }
    
    public String getPseudo()
    {
        return this.pseudo;
    }
    
    public String getMail()
    {
        return this.mail;
    }
    
    public int getYearOfBirth()
    {
        return this.yearOfBirth;
    }
    
    public void setColor(char color)
    {
        this.color = color;
    }
    
    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }
    
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    
    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }
    
    public void clone(Player player)
    {
        this.color = player.getColor();
        this.pseudo = player.getPseudo();
        this.mail = player.getMail();
        this.yearOfBirth = player.getYearOfBirth();
    }
    
    /**
     * @brief Crée un fichier binaire de joueur
     * @param player
     * @return 
     */
    public static boolean add(Player player)
    {
        ObjectOutputStream oos;
        File file;
        String nameFilePlayer;
        
        nameFilePlayer = "../player/" + player.getPseudo() + ".player";
        file = new File(nameFilePlayer);
        if ( !file.exists() )
        {
            try {
                oos = new ObjectOutputStream(
                            new BufferedOutputStream(
                                new FileOutputStream(file)));

                oos.writeObject(player);
                oos.close();
            } catch (FileNotFoundException e) {}
            catch (IOException e) {}
            return true;
        }
        return false;
    }
    
    /**
     * @brief Supprime un fichier bianire de joueur.
     * @param number
     * @return 
     */
    public static boolean delete(int number)
    {
        File folder;
        int count;

        count = 1;
        folder = new File("../player/");
        for ( File file : folder.listFiles())
        {
            try {
                if (count == number)
                {
                    file.delete();
                    return true;
                }
                ++count;
            } catch (NullPointerException e) {}
        }
        return false;
    }
    
    /**
     * @brief Charge le n ieme joueurs de la liste.
     * @param number
     * @return 
     */
    public static Player load(int number)
    {
        Player player;
        ObjectInputStream ois;
        int count;
        File folder;
        
        player = null;
        count = 1;
        folder = new File("../player/");
        for ( File file : folder.listFiles())
        {
            try {
                try {
                    ois = new ObjectInputStream(
                                new BufferedInputStream(
                                    new FileInputStream(file)));
                    try {
                          if (count == number)
                          {
                              player = (Player)ois.readObject();
                          }
                        ++count;
                        ois.close();
                    } catch (ClassNotFoundException e) {}
                } catch (FileNotFoundException e) {}
                catch (IOException e) {}
            } catch (NullPointerException e) {}
        }
        
        return player;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String str;
        str = this.color + " " + this.pseudo;
        return str;
    }
    
    public boolean equals(Player player)
    {
        return (this.pseudo == null ? player.getPseudo() == null : this.pseudo.equals(player.getPseudo()));
    }
}
