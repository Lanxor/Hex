package version2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import java.util.GregorianCalendar;

public class Saveguard {
    
    public static String listSaveguard()
    {
        File folder;
        String str;
        int numberOfSaveguards;
        
        str = "";
        numberOfSaveguards = 1;
        folder = new File("../save/");
        for ( File file : folder.listFiles())
        {
            try {
                str +=  numberOfSaveguards++ + " : " + file.getName() + "\n";
            } catch (NullPointerException e) {
                System.out.println("NULL");
            }
        }
        return str;
    }
    
    public static String getFormatSaveguard(Game game)
    {
        String str;
        
        str = "\\hex\n\\dim " + game.getDeck().getSize() + "\n";
        str += "\\player1 " + game.getPlayerCurrent().toString()+ "\n";
        str += "\\player2 " + game.getPlayer2().toString()+ "\n";
        str += "\\board\n" + game.getDeck().toString() + "\n\\endboard\n";
        str += "\\game\n" + game.getHistoric().toString() + "\\endgame\n";
        str += "\\endhex";
        return str;
    }
    
    public boolean isValid(File fileSaveguard)
    {
        return true;
    }
    
    /**
     * Partie Sauvegarde de donnée selon le format
     */
    
    
    public static boolean addSaveguard(Game game)
    {
        GregorianCalendar date;
        String str, nameFile;
        File f;
        FileWriter fw;
        
        date = new GregorianCalendar();
        nameFile = "../save/";
        nameFile += date.get(DAY_OF_YEAR) 
                + "_" + date.get(HOUR_OF_DAY)
                + "_" + date.get(MINUTE)
                + ".save";
        f = new File(nameFile);
        fw = null;
        
        try {
            fw = new FileWriter(f);
            str = Saveguard.getFormatSaveguard(game);
            fw.write(str);
        }
        catch (FileNotFoundException e) { return false; }
        catch (IOException e) { return false; }
        finally {
            try {
                if ( fw != null ) {
                    fw.close();
                    return true;
                }
            }
            catch (IOException e) { return false; }
        }
        return false;
    }
    
    /**
     * Partie qui permet de restaurer une partie
     */

    public static boolean loadSaveguard(Game game, int number)
    {
        File folder, file;
        int count, size;
        Player player1, player2;
        
        size = 0;
        file = null;
        
        // on initialise la partie
        game.getPlayerCurrent().setColor('b');
        game.getPlayer2().setColor('w');
        game.getHistoric().clean();
        game.getDeck().setSize(size);
        game.setNumberOfRound(1);
        
        /** Est une fonction **/
        count = 1;
        folder = new File("../save/");
        for ( File fileCurrent : folder.listFiles())
        {
            try {
                if ( count == number )
                {
                    file = fileCurrent;
                }
                ++count;
            }
            catch (NullPointerException e) { return false; }
        }
        
        // On détermine la taille du plateau
        
        if ( !Saveguard.loadSizeFile(file, game) )
            return false;
        Interface.showMessage("SizeGood\n");
        game.getDeck().createDeckC();
        if ( !Saveguard.loadPlayersSaveguard(file, game) )
            return false;
        Interface.showMessage("PlayerGood\n");
        if ( !Saveguard.loadMovesSaveguard(file, game) )
            return false;
        Interface.showMessage("MovesGood\n");
        
        System.out.println("Dimmension : " + game.getDeck().getSize());
        System.out.println("Player1 : " + game.getPlayerCurrent().toString());
        System.out.println("Player2 : " + game.getPlayer2().toString());
        
        return true;
    }
    
    private static boolean loadSizeFile(File file, Game game)
    {
        int size;
        int caracter;
        String str;
        FileReader fr;
        
        size = 0;
        str = null;
        fr = null;
        try {
            fr = new FileReader(file);
            str = "";
            caracter = 0;
            while ( (caracter = fr.read()) != -1 && !"\\dim ".equals(str) )
            {
                str += (char)caracter;
                if ( (char)caracter == '\n' )
                {
                    str = "";
                }
            }
            System.out.println((char)caracter);
            str = "";
            str += (char)caracter;
            while ( (caracter = fr.read()) != '\n' )
            {
                str += (char)caracter;
            }
            
        } catch ( FileNotFoundException e ) {}
        catch ( IOException e ) {}
        finally {
            try {
                if ( fr != null )
                    fr.close();
            } catch ( IOException e ) {}
        }
        
        if ( Deck.sizeValid(size = Integer.parseInt(str)) )
        {
            game.getDeck().setSize(size);
            return true;
        }
        return false;
    }
    
    private static boolean loadPlayersSaveguard(File fileSaveguard, Game game)
    {
        int caracter;
        char colorPlayer1, colorPlayer2;
        Player player1, player2;
        String str, namePlayer1, namePlayer2;
        FileReader fr;
        
        str = null;
        fr = null;
        namePlayer1 = "";
        namePlayer2 = "";
        player1 = null;
        player2 = null;
        colorPlayer1 = 'w';
        colorPlayer2 = 'w';
        try {
            fr = new FileReader(fileSaveguard);
            str = "";
            caracter = 0;
            while ( (caracter = fr.read()) != -1 && !"\\board".equals(str))
            {
                str += (char)caracter;
                if ( (char)caracter == '\n' )
                    str = "";
                
                if ( "\\player1 ".equals(str) )
                {
                    str = "";
                    colorPlayer1 = (char)fr.read();
                    fr.read();
                    while ( (caracter = fr.read()) != '\n' )
                    {
                        str += (char)caracter;
                    }
                    namePlayer1 = str;
                    str = "";
                }
                
                if ( "\\player2 ".equals(str) )
                {
                    str = "";
                    colorPlayer2 = (char)fr.read();
                    fr.read();
                    while ( (caracter = fr.read()) != '\n' )
                    {
                        str += (char)caracter;
                    }
                    namePlayer2 = str;
                    str = "";
                }
            }
            
        } catch ( FileNotFoundException e ) {}
        catch ( IOException e ) {}
        finally {
            try {
                if ( fr != null )
                    fr.close();
            } catch ( IOException e ) {}
        }
        
        player1 = Player.load(namePlayer1);
        
        if ( "Ordinateur".equals(namePlayer2) )
        {
            player2 = Player.getPlayerComputer();
        }
        else
        {
            player2 = Player.load(namePlayer2);
        }
        
        if ( player1.getColor() == player2.getColor() )
        {
            player2.setColor(Player.oppositeColor(player1.getColor()));
        }
        
        game.getPlayerCurrent().clone(player1);
        game.getPlayer2().clone(player2);
        
        
        Interface.showMessage(player1.toString() + "\n");
        Interface.showMessage(player2.toString() + "\n");
        
        return true;
    }
    
    private static boolean loadMovesSaveguard(File fileSaveguard, Game game)
    {
        Coordinates coordinates;
        Move move;
        Player player;
        int caracter, abscisse, ordonnee;
        char color;
        boolean isValid;
        FileReader fr;
        String str;
        
        fr = null;
        str = "";
        abscisse = 0;
        ordonnee = 0;
        isValid = true;
        try {
            fr = new FileReader(fileSaveguard);
            str = "";
            caracter = 0;
            while ((caracter = fr.read()) != -1)
            {
                str += (char)caracter;
                if ( (char)caracter == '\n' )
                    str = "";
                
                if ( "\\play ".equals(str) )
                {
                    color = (char)fr.read(); fr.read();
                    
                    str = "";
                    while ( (caracter = fr.read()) != ' ' )
                    {
                        str += (char)caracter;
                    }
                    abscisse = Integer.parseInt(str);
                    Interface.showMessage("Abscisse : " + str + "\n");
                    
                    str = "";
                    while ( (caracter = fr.read()) != '\n' )
                    {
                        str += (char)caracter;
                    }
                    ordonnee = Integer.parseInt(str);
                    Interface.showMessage("Ordonnee : " + str + "\n");
                    coordinates = new Coordinates(abscisse, ordonnee);
                    
                    if ( game.getPlayerCurrent().getColor() == color )
                    {
                        move = new Move(game.getPlayerCurrent(), coordinates);
                    }
                    else
                    {
                        move = new Move(game.getPlayer2(), coordinates);
                    }
                    Interface.showMessage(move.toString() + "\n");
                    if ( isValid && !game.playMove(move) )
                        isValid = false;
                    else
                    {
                        game.setNumberOfRound(game.getNumberOfRound() + 1);
                        System.out.println("Nombre : " + game.getNumberOfRound());
                        game.switchPlayer();
                        str = "";
                    }
                }
                
            }
        } catch (FileNotFoundException e) {}
        catch (IOException e) {}
        finally {
            try {
                if ( fr != null )
                    fr.close();
            } catch (IOException e) {}
        }
        
        
        return isValid;
    }

    /**
     * @brief Compte le nombre de sauvegarde enregistrer.
     * @return 
     */
    public static int getNumberOfSaveguard()
    {
        File folder;
        
        folder = new File("../save/");
        return folder.listFiles().length;
    }
    
    /**
     * @brief Indique si la liste des sauvegardes enregistrer est vide.
     * @return 
     */
    public static boolean emptySaveguard()
    {
        return Saveguard.getNumberOfSaveguard() < 1;
    }
    
}
