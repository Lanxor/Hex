package pkginterface;

public class Coordinates {
    
    private int abscisse;
    private int ordonnee;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @param abs
     * @param ord 
     */
    public Coordinates(int abs, int ord){
        this.abscisse = abs;
        this.ordonnee = ord;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                    Functions Static application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @param game
     * @return 
     */
    public static Coordinates askCoordinates(Game game)
    {
        int abscisse, ordonnee;
        Coordinates coordinates;
        
        System.out.println("Veuillez entrer des coordonnées.");
        System.out.print("Abscisse : ");
        abscisse = Interface.getInt(0, game.getDeck().getSize());
        System.out.print("Ordonnee : ");
        ordonnee = Interface.getInt(0, game.getDeck().getSize());
        coordinates = new Coordinates(abscisse, ordonnee);
        return coordinates;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Fonctions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @return 
     */
    public int getAbscisse()
    {
        return this.abscisse;
    }
    
    /**
     * 
     * @return 
     */
    public int getOrdonnee()
    {
        return this.ordonnee;
    }
    
    /**
     * 
     * @param abscisse 
     */
    public void setAbscisse(int abscisse)
    {
        this.abscisse = abscisse;
    }
    
    /**
     * 
     * @param ordonnee 
     */
    public void setOrdonnee(int ordonnee)
    {
        this.ordonnee = ordonnee;
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
        return this.getAbscisse() + " " + this.getOrdonnee();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                    Functions validation *
     *                                                                         *
     **************************************************************************/
    
    /**
     * 
     * @param game
     * @return 
     */
    public boolean isValid(Game game)
    {
        if ( (this.abscisse == 0 && this.ordonnee != 0)
                || (this.abscisse != 0 && this.ordonnee == 0) )
            return false;
        
        if ( this.abscisse > game.getDeck().getSize() 
                || this.ordonnee > game.getDeck().getSize() )
            return false;
        
        return true;
    }
    /**
     * 
     * @return 
     */
    public boolean isZero()
    {
        return this.abscisse == 0 && this.ordonnee == 0;
    }
}