package pkginterface;

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
    
    // DO NOT EDIT THIS LINE PLEASE
    private static final String FOLDER_SAVEGUARDS = "../save/";
    
    /***************************************************************************
     *                                                                         *
     *                                     Functions Getters, Setters advenced *
     *                                                                         *
     **************************************************************************/

    /**
     * @brief Fonction qui génère le nom d'un fichier de sauvegarde de partie.
     * @return Retourne la chaine de caractère du fichier de sauvegarde.
     */
    public static String getNameFileSaveguard()
    {
        GregorianCalendar date;
        String str;
        
        date = new GregorianCalendar();
        str = FOLDER_SAVEGUARDS;
        str += date.get(DAY_OF_YEAR) 
                + "_" + date.get(HOUR_OF_DAY)
                + "_" + date.get(MINUTE)
                + ".save";
        
        return str;
    }
    
    /**
     * @brief Fonction qui récupère un fichier de sauvegarde.
     * @param number : Numéro du fichier à récuppérer.
     * @return Retourne le fichier demandé.
     */
    public static File getFileSaveguard(int number)
    {
        File folder, file;
        int count;
        
        count = 1;
        file = null;
        folder = new File(FOLDER_SAVEGUARDS);
        for ( File fileCurrent : folder.listFiles())
        {
            try {
                if ( count == number )
                {
                    file = fileCurrent;
                }
                ++count;
            }
            catch (NullPointerException e) { return null; }
        }
        return file;
    }
    
    /**
     * @brief Fonction qui génère la chaine de caractère d'une partie de jeu.
     * @param game : Jeu auquel on joue.
     * @return Retourne la chaine de caractère de la sauvegarde au format
     * demandé.
     */
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
    
    /**
     * @brief Focntion qui récupère la liste des fichiers de sauvegarde. 
     * @return Retourne la list des fichiers de sauvegarde.
     */
    public static String[] getListSaveguard()
    {
        File folder;
        String list[];
        int count;
        
        list = new String[Saveguard.getNumberOfSaveguard()];
        count = 0;
        folder = new File(FOLDER_SAVEGUARDS);
        for ( File file : folder.listFiles())
        {
            try {
                list[count++] = file.getName() + "\n";
            } catch (NullPointerException e) { return null; }
        }
        return list;
    }
    
    /**
     * @brief Fonction qui compte le nombre de sauvegarde enregistrer.
     * @return Retourne le nombre de fichier de sauvegarde.
     */
    public static int getNumberOfSaveguard()
    {
        File folder;
        
        folder = new File(FOLDER_SAVEGUARDS);
        return folder.listFiles().length;
    }
    
    /**
     * @brief Fonction qui indique si la liste des sauvegardes enregistrer est vide.
     * @return Retourne si la liste de sauvegarde est vide ou non.
     */
    public static boolean emptySaveguard()
    {
        return Saveguard.getNumberOfSaveguard() < 1;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                     Functions Static internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui ajoute une sauvegarde.
     * @param game : Le jeu à sauvegarder.
     * @return Retourne si la partie à bien été sauvegarder ou non.
     */
    public static boolean addSaveguard(Game game)
    {
        String str, nameFile;
        File f;
        FileWriter fw;
        
        nameFile = Saveguard.getNameFileSaveguard();
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
                }
            }
            catch (IOException e) { return false; }
        }
        return true;
    }
    
    /**
     * @brief Fonction qui charge une sauvegarde.
     * @param game : Partie à charger.
     * @param number : Numéro de la partie à charger.
     * @return Retourne si la partie à été charger ou non.
     */
    public static boolean loadSaveguard(Game game, int number)
    {
        File file;
        int size;
        
        size = 0;
        file = null;
        
        // on initialise la partie
        game.getPlayerCurrent().setColor('b');
        game.getPlayer2().setColor('w');
        game.getHistoric().clean();
        game.getDeck().setSize(size);
        game.setNumberOfRound(1);
        
        file = Saveguard.getFileSaveguard(number);
        if ( !Saveguard.loadSizeFile(file, game) )
            return false;
        game.getDeck().createDeckC();
        if ( !Saveguard.loadPlayersSaveguard(file, game) )
            return false;
        if ( !Saveguard.loadMovesSaveguard(file, game) )
            return false;
        
        return true;
    }
    
    /**
     * @brief Fonction qui charge la taille d'un tablier.
     * @param file : Fichier à charger.
     * @param game : Game à charger.
     * @return Retourne si la taille à bien été charger.
     */
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
            str = "";
            str += (char)caracter;
            while ( (caracter = fr.read()) != '\n' )
            {
                str += (char)caracter;
            }
            
        } catch ( FileNotFoundException e ) { return false; }
        catch ( IOException e ) { return false; }
        finally {
            try {
                if ( fr != null )
                    fr.close();
            } catch ( IOException e ) { return false; }
        }
        
        if ( Deck.sizeValid(size = Integer.parseInt(str)) )
        {
            game.getDeck().setSize(size);
            return true;
        }
        return false;
    }
    
    /**
     * @brief Fonction qui charge les joueurs du partie.
     * @param fileSaveguard : Fichier à charger.
     * @param game : Partie à charger.
     * @return Retourne si la partie à bien été charger ou non.
     */
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
        
        player1 = Player.load(Player.getPathFilePlayer(namePlayer1+".player"));
        
        if ( "Ordinateur".equals(namePlayer2) )
        {
            player2 = Player.getPlayerComputer();
        }
        else
        {
            player2 = Player.load(Player.getPathFilePlayer(namePlayer2+".player"));
        }
        
        if ( player1.getColor() == player2.getColor() )
        {
            player2.setColor(Player.getOppositeColor(player1.getColor()));
        }
        
        game.getPlayerCurrent().copy(player1);
        game.getPlayer2().copy(player2);
        
        return true;
    }
    
    /**
     * @brief Fonction qui charge tout les coups d'une partie.
     * @param fileSaveguard : Fichier à charger.
     * @param game : Partie à charger.
     * @return Retourne si tout les coups on été charger.
     */
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
                    
                    str = "";
                    while ( (caracter = fr.read()) != '\n' )
                    {
                        str += (char)caracter;
                    }
                    ordonnee = Integer.parseInt(str);
                    coordinates = new Coordinates(abscisse, ordonnee);
                    
                    if ( game.getPlayerCurrent().getColor() == color )
                    {
                        move = new Move(game.getPlayerCurrent(), coordinates);
                    }
                    else
                    {
                        move = new Move(game.getPlayer2(), coordinates);
                    }
                    if ( isValid && !game.playMove(move) )
                        isValid = false;
                    else
                    {
                        game.setNumberOfRound(game.getNumberOfRound() + 1);
                        game.switchPlayer();
                        str = "";
                    }
                }
                
            }
        } catch (FileNotFoundException e) { return false; }
        catch (IOException e) { return false; }
        finally {
            try {
                if ( fr != null )
                    fr.close();
            } catch (IOException e) { return false; }
        }
        
        return isValid;
    }
    
}
