package pkginterface;

public class InterfaceJavaC {
    
    static
    {
        // DO NOT EDIT THIS LINE PLEASE
	System.load("/home/xiodi/Document/cours_save/Univ/L2/s4/projetHex/Hex/lib/libInterfaceC.so");
    }

    public native static void createDeck(int size);
    public native static void deleteDeck();

    public native static char getVerticeColor(int abscisse, int ordonnee);
    public native static void modifyVertice(char color, int abscisse, int ordonnee);
    public native static int isModifyVertice(char color, int abscisse, int ordonnee);
    public native static int hasWinner();
    public native static char getWinner();
}
