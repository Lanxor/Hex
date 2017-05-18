package pkginterface;

public class Deck {
    
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 19;
    private int size;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Constructeur par défaut.
     * @param size : Taille du plateau de jeu.
     */
    public Deck(int size)
    {
        if ( Deck.sizeValid(size) )
            this.size = size;
        else
            this.size = 0;
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
            InterfaceJavaC.createDeck(this.getSize());
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
     * @brief Focntion qui affiche le plateau de jeu.
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
    public int getSize(){
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