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
        for (int abscissa = 0; abscissa < 4; ++abscissa)
        {
            for (int orderly = 0; orderly < 4; ++orderly)
            {
                print("\tCréation d'une coordonnée ("+ abscissa +","+ orderly +")");
                c = new Coordinates(abscissa, orderly);
                print("\tAffichage de la coordonnée : ("+ c.getAbscissa() +","+ c.getOrderly() +")");
            }
        }
        print("Fin test print");
    }
    
    public static void test_valid()
    {
        Coordinates c;
        
        print("Test valid : ");
        for (int abscissa = 0; abscissa < 4; ++abscissa)
        {
            for (int orderly = 0; orderly < 4; ++orderly)
            {
                print("\tCréation d'une coordonnée ("+ abscissa +","+ orderly +")");
                c = new Coordinates(abscissa, orderly);
                print("\tAffichage de la coordonnée : ("+ c.getAbscissa() +","+ c.getOrderly() +")");
                for (int size = 0; size < 5; ++size)
                {
                    print("\tVérification pour une taille " + size + " : " + c.isValid(size));
                }
            }
        }
        print("Fin test valid");
    }
}
