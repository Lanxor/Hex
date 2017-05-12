package version2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        f = new File("save/");
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
        str += "\\player1 " + game.getPlayer1().toString()+ "\n";
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

    public static Game loadSaveguard(File fileSaveguard)
    {
        Game game;
        
        game = Saveguard.initializeLoadSaveguard(fileSaveguard);
        Saveguard.loadMove(fileSaveguard, game);
        
        System.out.println("Dimmension : " + game.getDeck().getSize());
        System.out.println("Player1 : " + game.getPlayer1().toString());
        System.out.println("Player2 : " + game.getPlayer2().toString());
        
        return null;
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
    
    public static File getFileSaveguard(int number)
    {
        int numberOfSaveguards;
        File f;
        
        numberOfSaveguards = 1;
        f = new File("save/");
        for ( File file : f.listFiles())
        {
            try {
                if ( numberOfSaveguards++ == number)
                {
                    return file;
                }
            } catch (NullPointerException e) {
                System.out.println("NULL");
            }
        }
        return null;
    }
}
