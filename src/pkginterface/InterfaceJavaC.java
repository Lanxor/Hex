/**
 * @file InterfaceJavaC.java
 * @author OLIVIER Thomas
 * @date 26 avril 2017
 * @brief Interface des fonctions du c 
 */

package pkginterface;

public class InterfaceJavaC {
    static
    {
        System.load("/home/lanx/Documents/Projet_S4/new_Hex/lib/libInterfaceC.so");
    }

    public native static void sayHello();

    public native static void createDeck(int size);
    public native static void deleteDeck();

    public native static void printDeckColor();

    public native static char getVerticeColor(int abscisse, int ordonnee);
    public native static void modifyVertice(char color, int abscisse, int ordonnee);
    public native static int isModifyVertice(char color, int abscisse, int ordonnee);
}
