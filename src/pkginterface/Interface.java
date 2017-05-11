/**
 * @file Deck.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Classe principale
 */

package pkginterface;

public class Interface {

    public static void main(String[] args)
    {
        InterfaceJavaC InterfaceJavaC = new InterfaceJavaC();

        System.out.println("Je dis Hello...");
        InterfaceJavaC.sayHello();
        System.out.println("Je crée le deck...");
        InterfaceJavaC.createDeck(5);
        System.out.println("J'affiche le deck...");
        InterfaceJavaC.printDeckColor();
        System.out.println("Je modifie le sommet 0 - 0");
        InterfaceJavaC.modifyVertice('b', 0, 0);
        System.out.println("J'affiche le deck...");
        InterfaceJavaC.printDeckColor();
        System.out.println("Je supprime le deck...");
        InterfaceJavaC.deleteDeck();
    }

}
