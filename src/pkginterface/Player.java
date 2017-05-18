package pkginterface;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable {
    
    // DO NOT EDIT THIS LINE PEASE
    private static final String FOLDER_PLAYER = "../player/";
    
    private char color;
    private String pseudo;
    private String mail;
    private int yearOfBirth;
    private Score score;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Constructeur par défaut.
     */
    public Player()
    {
        
    }
    
    /**
     * @brief Constructeur.
     * @param color : Couleur du joueur.
     */
    public Player(char color)
    {
        this.color = color;
    }
    
    /**
     * @brief Constructeur.
     * @param color : Couleur du joueur.
     * @param pseudo : Pseudo du joueur.
     */
    public Player(char color, String pseudo)
    {
        this.color = color;
        this.pseudo = pseudo;
        this.score = new Score();
    }
    
    /**
     * @brief Constructeur.
     * @param color : Couleur du joueur.
     * @param pseudo : Pseudo du joueur.
     * @param mail : Email du Joueur
     */
    public Player(char color, String pseudo, String mail)
    {
        this.color = color;
        this.pseudo = pseudo;
        this.mail = mail;
        this.score = new Score();
    }
    
    /**
     * @brief Constructeur.
     * @param color : Couleur du joueur.
     * @param pseudo : Pseudo du joueur.
     * @param mail : Email du joueur.
     * @param yearOfBirth : Année de naissance du joueur.
     */
    public Player(char color, String pseudo, String mail, int yearOfBirth)
    {
        this.color = color;
        this.pseudo = pseudo;
        this.mail = mail;
        this.yearOfBirth = yearOfBirth;
        this.score = new Score();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                   Functions Static menu *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui affiche le menu du Joueur. Et demande à
     * l'utilisateur de faire un choix.
     * @return Retourne le choix de l'utilisateur.
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
    
    /***************************************************************************
     *                                                                         *
     *                                                         Functions index *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction principale du joueur.
     * @param game : La jeu où le joueur est.
     */
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
    
    /***************************************************************************
     *                                                                         *
     *                                    Functions Static application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui affiche le profil du joueur.
     * @param game : Le jeux où le joueur est.
     */
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
                + "Année de naissance : " + player.getYearOfBirth() + "\n"
                + player.getScore().toString() + "\n";
        str += "-----------------------------\n";
        str += "-----------------------------\n";
        
        Interface.showMessage(str);
    }
    
    /**
     * @brief Change de joueur parmis la liste proposé.
     * @param game : Le jeux où le joueur est.
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
    
    /**
     * @brief Focntion qui demande à l'utilisateur l'ajout d'un nouveau joueur.
     * @return Retourne le nouveau joueur ajouté.
     */
    public static Player addPlayer()
    {
        Player player;
        
        player = Player.askNew();
        Player.add(player);
        return player;
    }
    
    /**
     * @brief Fonction qui demande à l'utilisateur qu'elle joueur supprimer
     * @param game : Le jeux où le joueur est.
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
     * @brief Demande à l'utilisateur les données d'un joueur.
     * @return Retourne un nouveau joueur.
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
     * @brief Fonction qui crée une chaine de la liste des joueurs existant.
     * @return Retourne une chaine de caractère de la liste des joueurs
     * existant.
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
        f = new File(FOLDER_PLAYER);
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
    
    /***************************************************************************
     *                                                                         *
     *                                              Functions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter couleur du joueur.
     * @return Retourne la couleur du joueur.
     */
    public char getColor()
    {
        return this.color;
    }
    
    /**
     * @brief Getter pseudo du joueur.
     * @return Retourne le pseudo du joueur.
     */
    public String getPseudo()
    {
        return this.pseudo;
    }
    
    /**
     * @brief Getter mail du joueur.
     * @return Retourne le mail du joueur.
     */
    public String getMail()
    {
        return this.mail;
    }
    
    /**
     * @brief Getter année de naissance du joueur.
     * @return Retourne l'année de naissance du joueur.
     */
    public int getYearOfBirth()
    {
        return this.yearOfBirth;
    }
    
    /**
     * @brief Getter score du joueur.
     * @return Retourn le score du joueur.
     */
    public Score getScore()
    {
        return this.score;
    }
    
    /**
     * @brief Setter couleur du joueur.
     * @param color : Nouvelle couleur du joueur.
     */
    public void setColor(char color)
    {
        this.color = color;
    }
    
    /**
     * @brief Setter pseudo du joueur.
     * @param pseudo : Nouveau pseudo du joueur.
     */
    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }
    
    /**
     * @brief Setter mail du joueur.
     * @param mail : Nouveau mail du joueur.
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    
    /**
     * @brief Setter année de naissance du joueur.
     * @param yearOfBirth : Nouvelle année de naissance du joueur.
     */
    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }
    
    /**
     * @brief Setter score du joueur.
     * @param score : Nouveau score du joueur.
     */
    public void setScore(Score score)
    {
        this.score = score;
    }
    
    /***************************************************************************
     *                                                                         *
     *                              Functions Static Getters, Setters advenced *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui renvoie le joueur ordinateur.
     * @return : Retourne un nouveau joueur ordianteur.
     */
    public static Player getPlayerComputer()
    {
        return new Player('w', "Ordinateur", "Thomas.olivier3@yahoo.fr", 2017);
    }
    
    /**
     * @brief Fonction qui compte le nombre de joueurs enregistrer.
     * @return Retourne le nombre total de joueur enregistré.
     */
    public static int getNumberOfPlayers()
    {
        File folder;
        
        folder = new File(FOLDER_PLAYER);
        return folder.listFiles().length;
    }
    
    /**
     * @brief Fonction qui ndique si la liste des joueurs enregistrer est vide.
     * @return Retourne si la liste des joueurs enregistrer est vide ou non.
     */
    public static boolean emptyPlayer()
    {
        return Player.getNumberOfPlayers() < 1;
    }
    
    /**
     * @brief Fonction qui renvoie la couleur opposé.
     * @param color : Couleur initialie.
     * @return Retourne la couleur opposé à la couleur initiale.
     */
    public static char getOppositeColor(char color)
    {
        if ( color == 'w' )
            return 'b';
        else if ( color == 'b' )
            return 'w';
        return 't';
    }
    
    /**
     * @brief Fonction qui renvoie un tableau de nom de fichier des joueurs
     * enregistrer.
     * @return Retourne la liste des fichiers des joueurs enregistrer.
     */
    public static String[] getNameFilePlayers()
    {
        File folder;
        String[] listNamePlayers;
        int count;
        
        listNamePlayers = new String[Player.getNumberOfPlayers()];
        count = 0;
        folder = new File(FOLDER_PLAYER);
        for (File file : folder.listFiles())
        {
            listNamePlayers[count++] = file.getName();
        }
        
        return listNamePlayers;
    }
    
    /**
     * @brief Fonction qui récupère tout les joueurs enregistrer.
     * @return Retourne un tableau de joueurs enregistrer.
     */
    public static Player[] getAllPlayers()
    {
        File file;
        Player[] listPlayers;
        int count;
        
        listPlayers = new Player[Player.getNumberOfPlayers()];
        count = 0;
        for ( String nameFile : Player.getNameFilePlayers() )
        {
            listPlayers[count++] = Player.load(Player.getPathFilePlayer(nameFile));
        }
        return listPlayers;
    }
    
    /**
     * @brief Fonction qui renvoie le chemin d'accés au fichier du joueur
     * enregistrer
     * @param namePlayer : Nom du fichier ciblé.
     * @return Retourne une chaine de caractère du chemin d'accés du fichier.
     */
    public static String getPathFilePlayer(String namePlayer)
    {
        return FOLDER_PLAYER + namePlayer;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Functions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui met à jour les scores d'un joueur quand une partie
     * est fini.
     */
    public void noWinner()
    {
        this.score.addGamePlayed();
    }
    
    /**
     * @brief Fonction qui met à jour les scores d'un joueur quand il gagne
     * une partie
     */
    public void win()
    {
        this.score.addGameWin();
    }
    
    /**
     * @brief Fontion qui met à jour les scores d'un joueur quand il perd
     * une partie
     */
    public void loose()
    {
        this.score.addGameLoose();
    }
    
    
    /**
     * @brief Fonction qui copie un joueur en fonction d'un autre.
     * @param player : Joueur à qui copier les données.
     */
    public void copy(Player player)
    {
        this.color = player.getColor();
        this.pseudo = player.getPseudo();
        this.mail = player.getMail();
        this.yearOfBirth = player.getYearOfBirth();
    }
    
    /**
     * @brief Fonction qui indique si deux joueurs sont identique.
     * @param player : Joueur avec qui comparer.
     * @return Retourne si le joueur est identique ou non.
     */
    public boolean equals(Player player)
    {
        return (this.pseudo == null ? player.getPseudo() == null : this.pseudo.equals(player.getPseudo()));
    }
    
    /**
     * @brief Fonction qui modifie la couleur du joueur.
     */
    public void changeColor()
    {
        if ( this.color == 'w' )
            this.color = 'b';
        else if ( this.color == 'b' )
            this.color = 'w';
    }
    
    /**
     * @brief Fonction qui définit un joueur.
     * @return Retourne une chaine de caractère définissant un joueur.
     */
    @Override
    public String toString()
    {
        String str;
        str = this.color + " " + this.pseudo;
        return str;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                     Functions Static internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui change la couleur du joueur actuelle dans une partie.
     * @param game : Le jeu où le joueur est modifier.
     */
    public static void changeColorPlayer(Game game)
    {
        game.getPlayerCurrent().changeColor();
    }
    
    /**
     * @brief Fonction qui charge un joueur au lancement du jeu.
     * @param game : Jeu auquel il faut charger les joueurs.
     * @return  Retourne le jeux intialiser.
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
     * @brief Fonction qui crée un fichier binaire de joueur.
     * @param player : Joueur à enregistrer;
     * @return Retourne si le joueur à bien été enregistré.
     */
    public static boolean add(Player player)
    {
        ObjectOutputStream oos;
        File file;
        String nameFile;
        
        nameFile = Player.getPathFilePlayer(player.pseudo + ".player");
        file = new File(nameFile);
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
     * @brief Fonction qui supprime un fichier bianire de joueur.
     * @param number : numéro du fichier à supprimer.
     * @return Retourne si le fichier à bien été supprimer.
     */
    public static boolean delete(int number)
    {
        File folder;
        int count;

        count = 1;
        folder = new File(FOLDER_PLAYER);
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
     * @brief Fonction qui charge le n ieme joueurs de la liste.
     * @param number : numéro du fichier à charger.
     * @return Retourne le joueur chargé.
     */
    public static Player load(int number)
    {
        Player player;
        ObjectInputStream ois;
        int count;
        File folder;
        
        player = null;
        count = 1;
        folder = new File(FOLDER_PLAYER);
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
     * @brief Fonction qui charge un fichier 
     * @param pathFilePlayer : Chaine de caractère du fichier à chager.
     * @return Retourne le joueur chargé.
     */
    public static Player load(String pathFilePlayer)
    {
        Player player;
        File file;
        ObjectInputStream ois;
        
        player = new Player();
        file = new File(pathFilePlayer);
        try {
            ois = new ObjectInputStream(
                        new BufferedInputStream(
                            new FileInputStream(file)));
            try {
                player = (Player)ois.readObject();
            } catch (ClassNotFoundException e) { return null; }
            ois.close();
        } catch (FileNotFoundException e) {return null; }
        catch (IOException e) {return null; }
        
        return player;
    }
}
