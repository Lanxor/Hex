package pkginterface;

public class InterfaceJavaC {
    
    static
    {
        // DO NOT EDIT THIS LINE PLEASE
	System.load("/home/lanx/Documents/Projet_S4/Hex/lib/libInterfaceC.so");
    }

    public native static void createDeck(int size);
    public native static void deleteDeck();

    public native static char getVerticeColor(int abscissa, int orderly);
    public native static void modifyVertice(char color, int abscissa, int orderly);
    public native static int isModifyVertice(char color, int abscissa, int orderly);
    public native static int hasWinner();
    public native static char getWinner();
}
