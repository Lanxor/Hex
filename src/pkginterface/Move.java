package pkginterface;

public class Move {
    
    private Player player;
    private Coordinates coordinates;
    
    public Move(Player player, Coordinates ccoordinates)
    {
        this.player = player;
        this.coordinates = coordinates;
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
    
    public Coordinates getCoordinates()
    {
        return this.coordinates;
    }
    
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    
    public void setCoordinates(Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }
    
    public boolean playMove()
    {
        if ( this.isValid() )
        {
            InterfaceJavaC.modifyVertice(this.player.getColor(),
                                    this.coordinates.getAbscisse(),
                                    this.coordinates.getOrdonnee());
            return true;
        }
        return false;
    }
    
    public boolean isValid()
    {
        int coordinatesValid;
        
        coordinatesValid = InterfaceJavaC.isModifyVertice(
                                    this.player.getColor(),
                                    this.coordinates.getAbscisse(),
                                    this.coordinates.getOrdonnee());
        return coordinatesValid == 1;
    }
    
    public String toString()
    {
        return "\\play " + this.player.getColor() 
                + " " 
                + this.coordinates.toString();
    }
    
}
