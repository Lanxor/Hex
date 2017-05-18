package pkginterface;

public class Move {
    
    private Player player;
    private Coordinates coordinates;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Constructeur par défaut.
     * @param player : Joueur associé au coup.
     * @param coordinates : Coordonnée associé au coup.
     */
    public Move(Player player, Coordinates coordinates)
    {
        this.player = player;
        this.coordinates = coordinates;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                    Functions Static application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Focntion qui demande à l'utilisateur de saisire un coup,
     * en fonction du joueur à qui est le tour de jouer.
     * @param game : Partie a laquel on demande de saisir un coup.
     * @return Retourne un nouveau coup initialiser.
     */
    public static Move askMove(Game game)
    {
        Player playerCurrent;
        Coordinates coordinates;
        
        if ( game.getWhoPlay() )
            playerCurrent = game.getPlayerCurrent();
        else
            playerCurrent = game.getPlayer2();
        coordinates = Coordinates.askCoordinates(game.getDeck().getSize());
        
        return new Move(playerCurrent, coordinates);
    }
    
    /**
     * @brief Fonction qui joue un coup sans vérification préalable. Est 
     * utiliser pour l'annulation d'un coup.
     * @param color : Couleur du coup jouer.
     * @param coordinates : Coordonnée à manipuler.
     */
    public static void play(char color, Coordinates coordinates)
    {
        InterfaceJavaC.modifyVertice(color,
                                coordinates.getAbscissa() - 1,
                                coordinates.getOrderly() - 1);
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Functions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter joueur du coup.
     * @return Retourne le joueur du coup.
     */
    public Player getPlayer()
    {
        return this.player;
    }
    
    /**
     * @brief Getter coordonnées du coup.
     * @return Retourne les coordonnées d'un coup.
     */
    public Coordinates getCoordinates()
    {
        return this.coordinates;
    }
    
    /**
     * @brief Setter joueur du coup.
     * @param player : Nouveau joueur du coup.
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    
    /**
     * @brief Setter coordonnées du coup.
     * @param coordinates : Nouvelle coordonnées du coup.
     */
    public void setCoordinates(Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Functions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui joue un coup.
     * @return Retourne si le coup à bien été jouer ou non.
     */
    public boolean play()
    {
        if ( this.isValid() || this.coordinates.isZero() )
        {
            InterfaceJavaC.modifyVertice(this.player.getColor(),
                                    this.coordinates.getAbscissa() - 1,
                                    this.coordinates.getOrderly() - 1);
            return true;
        }
        return false;
    }
    
    /**
     * @brief Fonction qui définit un coup.
     * @return Retourne une chaine de caractère définissant un coup.
     */
    @Override
    public String toString()
    {
        return "\\play " + this.player.getColor() 
                + " " 
                + this.coordinates.toString();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                    Functions validation *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui vérifie si un coup est valide.
     * @return Retourne si le coup est valide ou non.
     */
    public boolean isValid()
    {
        int coordinatesValid;
        
        coordinatesValid = InterfaceJavaC.isModifyVertice(
                                    this.player.getColor(),
                                    this.coordinates.getAbscissa() - 1,
                                    this.coordinates.getOrderly() - 1);
        return coordinatesValid == 1;
    }
    
    public boolean isNull()
    {
        return this.coordinates.isZero();
    }
}
