package pkginterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Deck extends JPanel {
    
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 19;
    private int size;
    private int xstart;
    private int ystart;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
        int x = xstart;
        int y = ystart;
        try {
            Image title = ImageIO.read(new File("title.png"));
            g.drawImage(title, 0, 0, this);
            Image hv = ImageIO.read(new File("hv.png"));
            Image hvtop = ImageIO.read(new File("hvtop.png"));
            Image hvbottom = ImageIO.read(new File("hvbottom.png"));
            Image hvright = ImageIO.read(new File("hvright.png"));
            Image hvleft = ImageIO.read(new File("hvleft.png"));
            Image hb = ImageIO.read(new File("hb.png"));
            Image hw = ImageIO.read(new File("hw.png"));
            for (int i = 1; i <= this.size; ++i){
                for (int j = 0; j < this.size; ++j){
                    /* rajouter if pour savoir couleur */
                    if (i == 1){
                        g.drawImage(hvtop, x, y, this);
                    }else if (i == this.size){
                        g.drawImage(hvbottom, x, y, this);
                    }else if (j == 0){
                        g.drawImage(hvleft, x, y, this);
                    }else if (j == this.size-1){
                        g.drawImage(hvright, x, y, this);
                    }else{
                        g.drawImage(hv, x, y, this);
                    }
                    x = x + 25;
                }
                x = xstart + 13 * i;
                y = y + 21;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @brief Constructeur par défaut.
     * @param size : Taille du plateau de jeu.
     */
    public Deck(int size)
    {
        this.size = size;
        this.xstart = 400 - 18 * size;
        this.ystart = 120;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                        Interface Java C *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui demande à la classe InterfaceJavaC de crée un
     * nouveau plateau de jeu en fonction de sa taille.
     */
    public void createDeckC()
    {
        if ( Deck.sizeValid(this.size) )
            InterfaceJavaC.createDeck(this.getSizeDeck());
    }
    
    /**
     * @brief Fonction qui demande à la classe InterfaceJavaC de supprimer
     * le plateau de jeu.
     */
    public void deleteDeckC()
    {
        if ( Deck.sizeValid(this.size) )
            InterfaceJavaC.deleteDeck();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                           Functions application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @biref Fonction qui demande à l'utilisateur la taille du tablier.
     * et met à jour le tablier de jeu.
     * @return Retourne la taille saisi par l'utilisateur.
     */
    public int askSize()
    {
        System.out.print("De quelle taille voulez-vous votre tablier ? ");
        this.size = Interface.getInt(MIN_SIZE, MAX_SIZE);
        return this.size;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                       Functions display *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonntion qui affiche le plateau de jeu en console.
     */
    public void print()
    {
        for (int bordureTop = 0; bordureTop < this.size; ++bordureTop )
            Interface.showMessage("*");
        Interface.showMessage("\n");
        
        for (int line = 0; line < this.size; ++line)
            Interface.showMessage("o " + this.getStringLine(line) + "o\n");
        
        for (int bordureTop = 0; bordureTop < this.size; ++bordureTop )
            Interface.showMessage("*");
        Interface.showMessage("\n");
        
        
    }
    
    
    
    /***************************************************************************
     *                                                                         *
     *                                             Functions Static validation *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui vérifie si la taille du plateau est valide ou non.
     * @param size : Taille du plateau à tester.
     * @return Retourne si la valeur est bien conforme.
     */
    public static boolean sizeValid(int size)
    {
        return  MIN_SIZE <= size && size <= MAX_SIZE;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Functions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter constant min_size.
     * @return Retourne la valeur minimale d'un tableau.
     */
    public static int getMinSize()
    {
        return MIN_SIZE;
    }
    
    /**
     * @brief Getter constant max_size.
     * @return Retourne la valeur maximal d'un plateau.
     */
    public static int getMaxSize()
    {
        return MAX_SIZE;
    }
    
    /**
     * @brief Getter size.
     * @return Retourne la taille du plateau.
     */
    public int getSizeDeck(){
        return this.size;
    }
    
    /**
     * @brief Setter size.
     * @param size : Taille du plateau.
     */
    public void setSize(int size)
    {
        this.size = size;
    }
    
    /**
     * @brief Fonction qui retourne la ligne voulu du tablier
     * @param numberOfLine : Numéro de la ligne voulu.
     * @return Retourne une chaine de caractère décrivant la ligne du tablier.
     */
    public String getStringLine(int numberOfLine)
    {
        String str;
        char color, symbol;
        
        str = "";
        for ( int orderly = 0; orderly < this.size; ++orderly)
        {
            color = InterfaceJavaC.getVerticeColor(numberOfLine, orderly);
            switch (color)
            {
                case 'b':
                    symbol = '*';
                    break;
                case 'w':
                    symbol = 'o';
                    break;
                default:
                    symbol = '.';
                    break;
            }
            str += symbol + " ";
        }
        
        return str;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Functions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui demande à la classe InterfaceJavaC les couleurs
     * de chaque case du tablier.
     * @return Retourne la chaine de caractère qui correspond au plateau
     * actuelle.
     */
    @Override
    public String toString()
    {
        String str = "\n";
        char color, symbol;
        for ( int abscissa = 0; abscissa < this.size; ++abscissa )
        {
            for (int orderly = 0; orderly < this.size; ++orderly )
            {   
                color = InterfaceJavaC.getVerticeColor(abscissa, orderly);
                switch (color) {
                    case 'b':
                        symbol = '*';
                        break;
                    case 'w':
                        symbol = 'o';
                        break;
                    default:
                        symbol = '.';
                        break;
                }
                
                str += symbol + " ";
            }
            str += "\n";
        }
        
        return str;
    }
    
}
