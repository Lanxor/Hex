package pkginterface;

public class Coordinates {
    
    private int abscissa;
    private int orderly;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Constructeur par défault.
     * @param abscissa : Abscissa de la coordonnée.
     * @param orderly : Ordonnée de la coordonnée.
     */
    public Coordinates(int abscissa, int orderly){
        this.abscissa = abscissa;
        this.orderly = orderly;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                    Functions Static application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui demande à l'utilisateur les coordonées.
     * @param size : Taille maximale autoriser.
     * @return Retourne un nouvelle object Coordinates.
     */
    public static Coordinates askCoordinates(int size)
    {
        int abscissa, orderly;
        
        System.out.println("Veuillez entrer des coordonnées.");
        System.out.print("Abscissa : ");
        abscissa = InterfaceConsole.getInt(0, size);
        System.out.print("Orderly : ");
        orderly = InterfaceConsole.getInt(0, size);
        
        return new Coordinates(abscissa, orderly);
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Fonctions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter abscissa.
     * @return Retourne l'abscissa de la coordonnée.
     */
    public int getAbscissa()
    {
        return this.abscissa;
    }
    
    /**
     * @biref Getter orderly.
     * @return Retourne l'ordonnée de la coordonnée.
     */
    public int getOrderly()
    {
        return this.orderly;
    }
    
    /**
     * @brief Setter abscissa.
     * @param abscissa : Abscissa de la coordonnée.
     */
    public void setAbscissa(int abscissa)
    {
        this.abscissa = abscissa;
    }
    
    /**
     * @brief Setter ordonnée.
     * @param orderly : Ordonnée de la coordonnée.
     */
    public void setOrderly(int orderly)
    {
        this.orderly = orderly;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Functions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui définit une coordonnée.
     * @return Retourne une chaine de caractère définissant la coordonnée.
     */
    @Override
    public String toString()
    {
        return this.getAbscissa() + " " + this.getOrderly();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                    Functions validation *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui vérifie la validité d'une coordonnée.
     * @param size : Taille maximale de la coordonnée.
     * @return Retourne si la coordonnée est valide ou non.
     */
    public boolean isValid(int size)
    {
        if ( (this.abscissa == 0 && this.orderly != 0)
                || (this.abscissa != 0 && this.orderly == 0) )
            return false;
        
        return !(this.abscissa > size
                || this.orderly > size);
    }
    /**
     * @brief Fonction qui définit si une coordonnée est égale à zero ou pas.
     * Elle permet de savoir si on veux annuler la saisie de coordonnée.
     * @return Retourne si la coordonnée est à zéro ou non.
     */
    public boolean isZero()
    {
        return this.abscissa == 0 && this.orderly == 0;
    }
}