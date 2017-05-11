
package pkginterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static pkginterface.Interface.getCoordinates;

public class Game {
    
    private Historic historic;
    private Deck deck;
    private Player player1;
    private Player player2;
    /**
     * @brief Définit si c'est au tour du joueur 1 de jouer ou non
     */
    private boolean round;
    
    public Game(Player player1, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(11);
        this.player1 = player1;
        this.player2 = player2;
        this.round = true;
        
        this.createDeck();
    }
    
    public Game(int size, Player player1, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(size);
        this.player1 = player1;
        this.player2 = player2;
        this.round = true;
        
        this.createDeck();
    }
    
    public void switchPlayer()
    {
        this.round = !this.round;
    }
    
    public void playMove()
    {
        Player playerCurrent;
        Coordinates coordinates;
        Move move;
        int coordinatesValid;
        
        if ( this.round )
            playerCurrent = this.player1;
        else
            playerCurrent = this.player2;
        
        do
        {
            coordinates = getCoordinates(this.deck);
            move = new Move(playerCurrent, coordinates);
            coordinatesValid = InterfaceJavaC.isModifyVertice(
                                    playerCurrent.getColor(),
                                    coordinates.getAbscisse(),
                                    coordinates.getOrdonnee());
            if ( coordinatesValid != 1 )
                System.out.println("Coordonnée impossible à ciblé.");
        } while ( coordinatesValid != 1 );
        
        this.historic.addMove(move);
        InterfaceJavaC.modifyVertice(playerCurrent.getColor(),
                                    coordinates.getAbscisse(),
                                    coordinates.getOrdonnee());
    }
    
    public void createDeck()
    {
        InterfaceJavaC.createDeck(this.deck.getSize());
    }
    
    public void saveInFile()
    {
        String str;
        String nameFile = "file_save";
        File f = new File(nameFile);
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            str = this.toStringFileSave();
            fw.write(str);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Player getPlayer1()
    {
        return this.player1;
    }
    
    public Player getPlayer2()
    {
        return this.player2;
    }
    
    public Historic getHistoric()
    {
        return this.historic;
    }
    
    public Deck getDeck()
    {
        return this.deck;
    }
    
    public void showDeck()
    {
        System.out.println("Affichage du tablier : ");
        InterfaceJavaC.printDeckColor();
    }
    
    public String toStringFileSave()
    {
        String str;
        
        str = "\\hex\n\\dim " + this.deck.getSize() + "\n";
        str += "\\board\n" + this.deck.toString() + "\n\\endboard\n";
        str += "\\game\n" + this.historic.toString() + "\n\\endgame\n";
        str += "\\endhex";
        return str;
    }
}
