package pkgtest;

import pkginterface.Deck;

public class test_deck {
    
    private static void print(String str)
    {
        System.out.println(str);
    }
    
    public static void main(String[] args)
    {
        print("Test unitaire : class Deck");
        test_create();
        print("Fin test unitaire");
    }
    
    private static void test_create()
    {
        Deck deck;
        
        print("Test create :");
        print("\tOn crée un deck de la taille 19");
        deck = new Deck(19);
        print("Fin de test");
    }
    
}
