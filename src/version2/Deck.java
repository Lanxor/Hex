package version2;

public class Deck {
    private int size;
    
    public Deck(int size)
    {
        this.size = size;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void setSize(int size)
    {
        this.size = size;
    }
    
    public void createDeckC()
    {
        InterfaceJavaC.createDeck(this.getSize());
    }
    
    public void deleteDeckC()
    {
        InterfaceJavaC.deleteDeck();
    }
    
    public int askSize()
    {
        System.out.println("De quelle taille voulez-vous votre tablier ? ");
        return Interface.getInt(5, 30);
    }
    
    public void print()
    {
        System.out.println(this.toString());
    }
    
    public String toString()
    {
        String str = "";
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