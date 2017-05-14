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
    
    public static int listSaveguard()
    {
        int numberOfSaveguards;
        File f;
        
        numberOfSaveguards = 1;
        f = new File("../save/");
        for ( File file : f.listFiles())
        {
            try {
            System.out.println(numberOfSaveguards++ + " : " + file.getName());
            } catch (NullPointerException e) {
                System.out.println("NULL");
            }
        }
        return numberOfSaveguards;
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
    
    
    public static void addSaveguard(Game game)
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( fw != null ) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            } catch (NullPointerException e) {}
        }
        
        // On détermine la taille du plateau
        size = Saveguard.loadSizeFile(file);
        System.out.println("Taille : " + game.getDeck().getSize());
        if ( size == -1 )
            return false;
        game.getDeck().setSize(size);
        // On détermine les deux joueurs pour les cloner
        //player1 = Saveguard.loadPlayer1(file);
        //player2 = Saveguard.loadPlayer2(file);
        //game.getPlayer1().clone(player1);
        //game.getPlayer2().clone(player2);
        // On joue la game pour chaque move
        
        System.out.println("Dimmension : " + game.getDeck().getSize());
        System.out.println("Player1 : " + game.getPlayerCurrent().toString());
        System.out.println("Player2 : " + game.getPlayer2().toString());
        
        return true;
    }
    
    private static int loadSizeFile(File file)
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
            while ( (caracter = fr.read()) != -1 )
            {
                str += (char)caracter;
            }
            System.out.println("Chaine : " + str);
            
        } catch ( FileNotFoundException e ) {}
        catch ( IOException e ) {}
        finally {
            try {
                if ( fr != null )
                    fr.close();
            } catch ( IOException e ) {}
        }
        
        if (Deck.sizeValid(size))
            return size;
        return -1;
    }
    
    private static Game initializeLoadSaveguard(File fileSaveguard)
    {
        Player player1, player2;
        int dim, caracter;
        FileReader fr;
        String str;
        
        fr = null;
        dim = 0;
        player1 = null;
        player2 = null;
        
        try {
            fr = new FileReader(fileSaveguard);
            str = "";
            caracter = 0;
            while ((caracter = fr.read()) != -1)
            {
                str += (char)caracter;
                switch (str)
                {
                    case "\\hex\n":
                        str = "";
                        break;
                    case "\\player1 ":
                        str = "";
                        player1 = new Player((char)fr.read());
                        fr.read();
                        while ( (char)caracter != '\n' )
                        {
                            caracter = fr.read();
                            System.out.println((int)caracter);
                            str += (char)caracter;
                        }
                        player1.setPseudo(str);
                        str = "";
                        break;
                    case "\\player2 ":
                        str = "";
                        player2 = new Player((char)fr.read());
                        fr.read();
                        while ( (char)caracter != '\n' )
                        {
                            caracter = fr.read();
                            System.out.println((int)caracter);
                            str += (char)caracter;
                        }
                        player2.setPseudo(str);
                        str = "";
                        break;
                    case "\\dim ":
                        str = "";
                        while ( (char)caracter != '\n' )
                        {
                            caracter = fr.read();
                            System.out.println((int)caracter);
                            if ( (int)caracter >= 48 && (int)caracter <= 57 )
                                dim += dim*10 + ((int)caracter-48);
                        }
                        str = "";
                        break;
                    case "\\game":
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( fr != null ) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Game(dim, player1, player2);
    }
    
    private static void loadMove(File fileSaveguard, Game game)
    {
        int caracter;
        FileReader fr;
        String str;
        Move move;
        
        fr = null;
        move = null;
        
        try {
            fr = new FileReader(fileSaveguard);
            str = "";
            caracter = 0;
            while ((caracter = fr.read()) != -1)
            {
                str += (char)caracter;
                switch (str)
                {
                    case "\\play ":
                        str = "";
                        while ( (char)caracter != '\n' )
                        {
                        }
                        str = "";
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( fr != null ) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
