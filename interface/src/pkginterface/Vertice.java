/**
 * @file Vertice.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe de gestion des sommets
 */

package pkginterface;

public class Vertice {
    private String color;
    
    public Vertice()
    {
        this.color = "transparant";
    }
    
    public String getColor()
    {
        return this.color;
    }
    
    public String displayVertice()
    {
        if (this.color.equals("black"))
            return "| • ";
        if (this.color.equals("white"))
            return "| ○ ";
        return "|   ";
    }
    
}
