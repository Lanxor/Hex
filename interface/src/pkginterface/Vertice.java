/**
 * @file Vertice.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des sommets
 */

package pkginterface;

public class Vertice {
    private char color;
    private Coordinates coordinates;
    
    public Vertice(Coordinates coordinates)
    {
        this.color = 't';
        this.coordinates = coordinates;
    }
    
    public Vertice(char color, Coordinates coordinates)
    {
        this.color = color;
        this.coordinates = coordinates;
    }
    
    public char getColor()
    {
        return this.color;
    }
    
    public Coordinates getCoordinates()
    {
        return this.coordinates;
    }
    
}
