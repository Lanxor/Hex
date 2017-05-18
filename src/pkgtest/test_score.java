package pkgtest;

import pkginterface.Score;

public class test_score {
    
    private static void print(String str)
    {
        System.out.println(str);
    }
    
    public static void main(String[] args)
    {
        print("Test unitaire : class Score");
        test_win();
        test_loose();
        print("Fin test unitaire");
    }
    
    private static void test_win()
    {
        Score score;
        
        print("Test create : ");
        print("\tCréation d'un score vide.");
        score = new Score();
        print("Affichage du score : \n" + score.toString());
        print("Ajouter une partie gagner au score.");
        score.addGameWin();
        print("Affichage du score : \n" + score.toString());
        print("Fin test");
        
    }
    
    private static void test_loose()
    {
        Score score;
        
        print("Test create : ");
        print("\tCréation d'un score vide.");
        score = new Score();
        print("Affichage du score : \n" + score.toString());
        print("Ajouter une partie gagner au score.");
        score.addGameLoose();
        print("Affichage du score : \n" + score.toString());
        print("Fin test");
        
    }    
}
