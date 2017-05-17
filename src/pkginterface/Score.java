package pkginterface;

import java.io.File;
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
     * 
     */
    public Score()
    {
        this.gamePlayed = 0;
        this.gameWin = 0;
        this.gameLoose = 0;
    }
    
    /**
     * 
     * @param gamePlayed
     * @param gameWin
     * @param gameLoose 
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
     * 
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
    
    public void addGamePlayed()
    {
        ++this.gamePlayed;
    }
    
    public void addGameWin()
    {
        ++this.gamePlayed;
        ++this.gameWin;
    }
    
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
     * 
     * @return 
     */
    public int getGamePlayed()
    {
        return this.gamePlayed;
    }
    
    /**
     * 
     * @return 
     */
    public int getGameWin()
    {
        return this.gameWin;
    }
    
    /**
     * 
     * @return 
     */
    public int getGameLoose()
    {
        return this.gameLoose;
    }
    
    public void setGamePlayed(int gamePlayed)
    {
        this.gamePlayed = gamePlayed;
    }
    
    /**
     * 
     * @param gameWin 
     */
    public void setGameWin(int gameWin)
    {
        this.gameWin = gameWin;
    }
    
    /**
     * 
     * @param gameLoose 
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
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        String str;
        
        str = "\tPartie jouée : " + this.gamePlayed + "\n";
        str += "\tPartie gagnée : " + this.gameWin + "\n";
        str += "\tPartie perdu : " + this.gameLoose;
        
        return str;
    }
}
