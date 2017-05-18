package pkgtest;

import pkginterface.Historic;
import pkginterface.Coordinates;
import pkginterface.Player;
import pkginterface.Move;

public class test_historic {
    
    private static void print(String str)
    {
        System.out.println(str);
    }
    
    public static void main(String[] args)
    {
        print("Test unitaire : class Historic");
        test_ajout();
        test_lastMove();
        test_deleteLastMove();
        test_printHistoric();
        print("Fin test unitaire");
    }
    
    private static void test_ajout()
    {
        Historic historic;
        
        print("Test ajout : ");
        historic = new Historic();
        print("\tL'historique est-il vide : " + historic.isEmpty());
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('w'), new Coordinates(0,0) ));
        print("\tL'historique est-il vide : " + historic.isEmpty());
        print("Fin test");
    }
    
    private static void test_lastMove()
    {
        Historic historic;
        print("Test dernier coup joué : ");
        historic = new Historic();
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('w'), new Coordinates(0,0) ));
        print("Affichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('b'), new Coordinates(0,1) ));
        print("\tAffichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("Fin test");
    }
    
    private static void test_deleteLastMove()
    {
        Historic historic;
        
        print("Test suppression du dernier coup : ");
        historic = new Historic();
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('w'), new Coordinates(0,0) ));
        print("Affichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('b'), new Coordinates(0,1) ));
        print("\tAffichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("\tOn supprime le dernier coup jouer");
        historic.deleteLastMove();
        print("\tAffichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("Fin test");
    }
    
    private static void test_printHistoric()
    {
        Historic historic;
        
        print("Test print historique : ");
        historic = new Historic();
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('w'), new Coordinates(0,0) ));
        print("Affichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('b'), new Coordinates(0,1) ));
        print("\tAffichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("\tOn ajoute une coup");
        historic.addMove(new Move(new Player('w'), new Coordinates(0,2) ));
        print("\tAffichage du dernier coup jouer : " + historic.getLastMove().toString());
        print("\tAffichage de toute l'historique selon le format demander : ");
        print(historic.toString());
        print("Fin test");
    }
}
