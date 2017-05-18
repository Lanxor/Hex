package pkginterface;

import java.io.Serializable;

public class Score implements Serializable {
    
    private int gamePlayed;
    private int gameWin;
    private int gameLoose;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Constructeur par défaut.
     */
    public Score()
    {
        this.gamePlayed = 0;
        this.gameWin = 0;
        this.gameLoose = 0;
    }
    
    /**
     * @brief Constructeur
     * @param gamePlayed : Nombre de partie jouer.
     * @param gameWin : Nombre de partie gagnée.
     * @param gameLoose : Nombre de partie perdu.
     */
    public Score(int gamePlayed, int gameWin, int gameLoose)
    {
        this.gamePlayed = gamePlayed;
        this.gameWin = gameWin;
        this.gameLoose = gameLoose;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                  Functions Static index *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonctionq principale.
     */
    public static void index()
    {
        Score.showScoreOfPlayers();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                Fonctions Static display *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui affiche les cores des joueurs enregistrer.
     */
    public static void showScoreOfPlayers()
    {
        Player[] listPlayers;
        
        listPlayers = Player.getAllPlayers();
        for ( Player player : listPlayers )
        {
            Interface.showMessage(player.getPseudo() + " :\n" + player.getScore().toString());
        }
    }
    
    /***************************************************************************
     *                                                                         *
     *                                           Functions application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui ajoute une game jouer.
     */
    public void addGamePlayed()
    {
        ++this.gamePlayed;
    }
    
    /**
     * @brief Fonction qui ajoute une partie gagnée.
     */
    public void addGameWin()
    {
        ++this.gamePlayed;
        ++this.gameWin;
    }
    
    /**
     * @brief Fonction qui ajoute une partie perdu.
     */
    public void addGameLoose()
    {
        ++this.gamePlayed;
        ++this.gameLoose;
    }
    
    
    /***************************************************************************
     *                                                                         *
     *                                              Fonctions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter partie jouée.
     * @return Retourne le nombre de partie jouer.
     */
    public int getGamePlayed()
    {
        return this.gamePlayed;
    }
    
    /**
     * @brief Getter partie gagnée
     * @return Retourne le nombre de partie gagnée.
     */
    public int getGameWin()
    {
        return this.gameWin;
    }
    
    /**
     * @brief Getter partie perdu.
     * @return Retourne le nombre de partie perdu.
     */
    public int getGameLoose()
    {
        return this.gameLoose;
    }
    
    /**
     * @brief Setter partie jouée.
     * @param gamePlayed : Nouveau nombre de partie jouée.
     */
    public void setGamePlayed(int gamePlayed)
    {
        this.gamePlayed = gamePlayed;
    }
    
    /**
     * @brief Setter partie gagnée.
     * @param gameWin : Nouveau nombre de partie gagnée.
     */
    public void setGameWin(int gameWin)
    {
        this.gameWin = gameWin;
    }
    
    /**
     * @brief Setter partie perdu.
     * @param gameLoose : Nouveau nombre de partie perdu.
     */
    public void setGameLoose(int gameLoose)
    {
        this.gameLoose = gameLoose;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Functions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui définit un score.
     * @return Retourne une chaine de caractère définissant un score.
     */
    @Override
    public String toString()
    {
        String str;
        
        str = "\tPartie jouée : " + this.gamePlayed + "\n";
        str += "\tPartie gagnée : " + this.gameWin + "\n";
        str += "\tPartie perdu : " + this.gameLoose + "\n";
        
        return str;
    }
}
