/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

/**
 *
 * @author Eléana
 */
public class InterfaceSwing {
    
    private static Game game;
    
    /***************************************************************************
     *                                                                         *
     *                                                           Function Main *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction principale du programme de jeu HEX
     * @param args 
     */
    public static void main(String[] args)
    {
        game = new Game();
        Fenetre fenetre = new Fenetre(game);
    }
    
    public static Game getGame()
    {
        return game;
    }
    
    public static void setGame(Game newGame)
    {
        game = newGame;
    }
    
}
