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

    public native void sayHello();

    public native void createDeck(int size);
    public native void deleteDeck();

    public native void printDeckColor();

    public native void modifyVertice(char color, int abscisse, int ordonnee);
    public native int isModifyVertice(char color, int abscisse, int ordonnee);
}
