/**
 * @file Cell.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion de la cellule
 */

package pkginterface;

public class Cell {
    private String color;
    
    public Cell()
    {
        this.color = "empt";
    }
    
    public String getColor()
    {
        return this.color;
    }
    
    public void placeStone(String color)
    {
        this.color = color;
    }
}
