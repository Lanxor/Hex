package version2;

public class InterfaceJavaC {
    static
    {
        System.load("/home/lanx/Documents/Projet_S4/Hex/lib/libInterfaceC.so");
    }

    public native static void createDeck(int size);
    public native static void deleteDeck();

    public native static char getVerticeColor(int abscisse, int ordonnee);
    public native static void modifyVertice(char color, int abscisse, int ordonnee);
    public native static int isModifyVertice(char color, int abscisse, int ordonnee);
}
