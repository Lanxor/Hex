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
        System.load("/home/lanx/Documents/Projet_S4/Hex/interface/src/librairiC.so");
    }
    
    public native void sayHello();
    public native int createDeck(int size);
    public native void deleteDeck(int deck);
}
