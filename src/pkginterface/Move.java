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
     * 
     * @param player
     * @param coordinates 
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
     * 
     * @param game
     * @return 
     */
    public static Move askMove(Game game)
    {
        Player playerCurrent;
        Coordinates coordinates;
        Move move;
        
        if ( game.getwhoPlay() )
            playerCurrent = game.getPlayerCurrent();
        else
            playerCurrent = game.getPlayer2();
        coordinates = Coordinates.askCoordinates(game);
        move = new Move(playerCurrent, coordinates);
        
        return move;
    }
    
    /**
     * 
     * @param color
     * @param coordinates 
     */
    public static void play(char color, Coordinates coordinates)
    {
        InterfaceJavaC.modifyVertice(color,
                                coordinates.getAbscisse() - 1,
                                coordinates.getOrdonnee() - 1);
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
    public Player getPlayer()
    {
        return this.player;
    }
    
    /**
     * 
     * @return 
     */
    public Coordinates getCoordinates()
    {
        return this.coordinates;
    }
    
    /**
     * 
     * @param player 
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    
    /**
     * 
     * @param coordinates 
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
     * 
     * @return 
     */
    public boolean play()
    {
        if ( this.isValid() || this.coordinates.isZero() )
        {
            InterfaceJavaC.modifyVertice(this.player.getColor(),
                                    this.coordinates.getAbscisse() - 1,
                                    this.coordinates.getOrdonnee() - 1);
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return 
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
     * 
     * @return 
     */
    public boolean isValid()
    {
        int coordinatesValid;
        
        coordinatesValid = InterfaceJavaC.isModifyVertice(
                                    this.player.getColor(),
                                    this.coordinates.getAbscisse() - 1,
                                    this.coordinates.getOrdonnee() - 1);
        return coordinatesValid == 1;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isNull()
    {
        return this.coordinates.isZero();
    }
}
