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
        menu[0] = "Profile";
        menu[1] = "Changer de couleur du personnage";
        menu[2] = "Changer de personnage";
        menu[3] = "Ajouter un personnage";
        menu[4] = "Supprimer un personnage";
        menu[5] = "Retour";
        Interface.printMenu(menu);
        System.out.print("Choix : ");
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
                    System.out.println("\nVoici votre profil : ");
                    Player.showProfilPlayer(game);
                    break;
                case 2: // Changer de couleur de joueur
                    Player.changeColorPlayer(game);
                    System.out.println("\nVous venez de changer de couleur.");
                    break;
                case 3: // Changer de joueur
                    Player.changePlayer(game);
                    System.out.println("\nVous venez de changer de joueur.");
                    break;
                case 4: // Ajouter un joueur
                    Player.addPlayer();
                    System.out.println("\nVous venez d'ajouter un joueur.");
                    break;
                case 5: // Supprimer un joueur
                    Player.deletePlayer(game);
                    System.out.println("\nVous venez de supprimer un joueur.");
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
        str += "-----------------------------";
        
        System.out.println(str);
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
        
        System.out.println(Player.listPlayer());
        System.out.print("Choississez un joueur : ");
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
            System.out.println(Player.listPlayer());
            System.out.println("Tapez 0 pour quitter.");
            System.out.print("Choississez un joueur : ");
            numberPlayer = Player.getNumberOfPlayers();
            choice = Interface.getInt(1, numberPlayer);
            player = Player.load(choice);
            if ( game.getPlayerCurrent().equals(player) )
                System.out.println("Vous ne pouvez vous selectionner.");
        } while ( game.getPlayerCurrent().equals(player) );
        Player.delete(choice);
        System.out.println("Joueur supprimé.");
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
        Scanner sc;
        
        sc = new Scanner(System.in);
        System.out.println("Nouveau joueur.");
        System.out.print("Saississez un pseudonyme : ");
        pseudo = sc.nextLine();
        System.out.print("Saississez votre email : ");
        mail = sc.nextLine();
        System.out.print("Saississez votre année de naissance : ");
        yearOfBirth = Interface.getInt(1950, 2017);
        color = 'w';
        do {
            System.out.print("Quel couleur souhaitez vous ? (Blanc/Noir) : ");
        } while ( !"Blanc".equals(colorStr = Interface.getString()) && !"Noir".equals(colorStr) );
        
        switch(colorStr)
        {
            case "blanc":
                color = 'w';
                break;
            case "noir":
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
