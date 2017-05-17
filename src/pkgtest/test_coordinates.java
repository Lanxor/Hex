package pkgtest;

import pkginterface.Coordinates;

public class test_coordinates {
    
    public static void print(String str)
    {
        System.out.println(str);
    }
    
    public static void main(String[] args)
    {
        print("Test unitaire : class Coordinates");
        test_print();
        test_valid();
        print("Fin test unitaire");
    }
    
    public static void test_print()
    {
        Coordinates c;
        
        print("Test print : ");
        for (int abscisse = 0; abscisse < 4; ++abscisse)
        {
            for (int ordonnee = 0; ordonnee < 4; ++ordonnee)
            {
                print("\tCréation d'une coordonnée ("+ abscisse +","+ ordonnee +")");
                c = new Coordinates(abscisse, ordonnee);
                print("\tAffichage de la coordonnée : ("+ c.getAbscisse() +","+ c.getOrdonnee() +")");
            }
        }
        print("Fin test print");
    }
    
    public static void test_valid()
    {
        Coordinates c;
        
        print("Test valid : ");
        for (int abscisse = 0; abscisse < 4; ++abscisse)
        {
            for (int ordonnee = 0; ordonnee < 4; ++ordonnee)
            {
                print("\tCréation d'une coordonnée ("+ abscisse +","+ ordonnee +")");
                c = new Coordinates(abscisse, ordonnee);
                print("\tAffichage de la coordonnée : ("+ c.getAbscisse() +","+ c.getOrdonnee() +")");
                for (int size = 0; size < 5; ++size)
                {
                    print("\tVérification pour une taille " + size + " : " + c.isValid(size));
                }
            }
        }
        print("Fin test valid");
    }
}
