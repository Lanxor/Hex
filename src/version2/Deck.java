package version2;

public class Deck {
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 19;
    private int size;
    
    public Deck(int size)
    {
        this.size = size;
    }
    
    public static boolean sizeValid(int size)
    {
        return  MIN_SIZE <= size && size <= MAX_SIZE;
    }
    
    public void createDeckC()
    {
        InterfaceJavaC.createDeck(this.getSize());
    }
    
    public void deleteDeckC()
    {
        InterfaceJavaC.deleteDeck();
    }
    
    public static int askSize()
    {
        System.out.print("De quelle taille voulez-vous votre tablier ? ");
        return Interface.getInt(MIN_SIZE, MAX_SIZE);
    }
    
    public void print()
    {
        System.out.println(this.toString());
    }
    
    public static int getMinSize()
    {
        return MIN_SIZE;
    }
    
    public static int getMaxSize()
    {
        return MAX_SIZE;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void setSize(int size)
    {
        this.size = size;
    }
    
    public String toString()
    {
        String str = "\n";
        char color, symbol;
        for ( int abscisse = 0; abscisse < this.size; ++abscisse )
        {
            for (int ordonnee = 0; ordonnee < this.size; ++ordonnee )
            {   
                color = InterfaceJavaC.getVerticeColor(abscisse, ordonnee);
                if ( color == 'b' )
                    symbol = '*';
                else if ( color == 'w' )
                    symbol = 'o';
                else
                    symbol = '.';
                
                str += symbol + " ";
            }
            str += "\n";
        }
        
        return str;
    }
    
}