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
     * 
     * @param size 
     */
    public Deck(int size)
    {
        this.size = size;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                        Interface Java C *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     */
    public void createDeckC()
    {
        InterfaceJavaC.createDeck(this.getSize());
    }
    
    /**
     * 
     */
    public void deleteDeckC()
    {
        InterfaceJavaC.deleteDeck();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                           Functions application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @return 
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
     * 
     */
    public void print()
    {
        System.out.println(this.toString());
    }
    
    /***************************************************************************
     *                                                                         *
     *                                             Functions Static validation *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @param size
     * @return 
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
     * 
     * @return 
     */
    public static int getMinSize()
    {
        return MIN_SIZE;
    }
    
    /**
     * 
     * @return 
     */
    public static int getMaxSize()
    {
        return MAX_SIZE;
    }
    
    /**
     * 
     * @return 
     */
    public int getSize(){
        return this.size;
    }
    
    /**
     * 
     * @param size 
     */
    public void setSize(int size)
    {
        this.size = size;
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
        String str = "\n";
        char color, symbol;
        for ( int abscisse = 0; abscisse < this.size; ++abscisse )
        {
            for (int ordonnee = 0; ordonnee < this.size; ++ordonnee )
            {   
                color = InterfaceJavaC.getVerticeColor(abscisse, ordonnee);
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