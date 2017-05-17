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
     * @brief Constructeur par défault.
     * @param abscisse : Abscisse de la coordonnée.
     * @param ordonnee : Ordonnée de la coordonnée.
     */
    public Coordinates(int abscisse, int ordonnee){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
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
        int abscisse, ordonnee;
        
        System.out.println("Veuillez entrer des coordonnées.");
        System.out.print("Abscisse : ");
        abscisse = Interface.getInt(0, size);
        System.out.print("Ordonnee : ");
        ordonnee = Interface.getInt(0, size);
        
        return new Coordinates(abscisse, ordonnee);
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Fonctions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter abscisse.
     * @return Retourne l'abscisse de la coordonnée.
     */
    public int getAbscisse()
    {
        return this.abscisse;
    }
    
    /**
     * @biref Getter ordonnee.
     * @return Retourne l'ordonnée de la coordonnée.
     */
    public int getOrdonnee()
    {
        return this.ordonnee;
    }
    
    /**
     * @brief Setter abscisse.
     * @param abscisse : Abscisse de la coordonnée.
     */
    public void setAbscisse(int abscisse)
    {
        this.abscisse = abscisse;
    }
    
    /**
     * @brief Setter ordonnée.
     * @param ordonnee : Ordonnée de la coordonnée.
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
     * @brief Fonction qui définit une coordonnée.
     * @return Retourne une chaine de caractère définissant la coordonnée.
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
     * @brief Fonction qui vérifie la validité d'une coordonnée.
     * @param size : Taille maximale de la coordonnée.
     * @return Retourne si la coordonnée est valide ou non.
     */
    public boolean isValid(int size)
    {
        if ( (this.abscisse == 0 && this.ordonnee != 0)
                || (this.abscisse != 0 && this.ordonnee == 0) )
            return false;
        
        return !(this.abscisse > size
                || this.ordonnee > size);
    }
    /**
     * @brief Fonction qui définit si une coordonnée est égale à zero ou pas.
     * Elle permet de savoir si on veux annuler la saisie de coordonnée.
     * @return Retourne si la coordonnée est à zéro ou non.
     */
    public boolean isZero()
    {
        return this.abscisse == 0 && this.ordonnee == 0;
    }
}