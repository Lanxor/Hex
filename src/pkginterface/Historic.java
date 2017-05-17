package pkginterface;

import java.util.ArrayList;

public class Historic {
    
    private ArrayList<Move> moves;
    private int numberOfMove;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Constructeur par défaut.
     */
    public Historic()
    {
        this.moves = new ArrayList<>();
        this.numberOfMove = 0;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Functions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter liste de coups.
     * @return Retourne la liste des coups de l'historique.
     */
    public ArrayList getMoves()
    {
        return this.moves;
    }
    
    /**
     * @brief Getter nombre de coups
     * @return Retourne le nombre de coups
     */
    public int getNumberOfMove()
    {
        return this.numberOfMove;
    }
    
    /**
     * @brief Setter nombre de coups.
     * @param numberOfMove : Nombre de coups de l'historique.
     */
    public void setNumberOfMove(int numberOfMove)
    {
        this.numberOfMove = numberOfMove;
    }
    
    /**
     * @brief Setter liste de coups
     * @param moves : Liste de coups de l'historique.
     */
    public void setMoves(ArrayList<Move> moves)
    {
        this.moves = moves;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                     Functions Getters, Setters advenced *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui modifie un coup particulier dans l'historique.
     * @param move : Move à remplacer.
     * @param number : numéro du coup à modifier.
     */
    public void setMove(Move move, int number)
    {
        this.moves.set(number-1, move);
    }
    
    /**
     * @brief Fonction qui récupère le coups à une place donnée.
     * @param number : Numéro du coup à modifier.
     * @return Retourne le coup demander.
     */
    public Move getMove(int number)
    {
        return (Move)this.moves.get(number-1);
    }
    
    /**
     * @brief Fonction qui retourne le dernier coup de l'historique.
     * @return Retourne le dernier coup de l'historique.
     */
    public Move getLastMove()
    {
        return (Move)this.getMove(this.numberOfMove);
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Functions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui ajoute un coup à la fin de l'historique.
     * @param move : Coup à ajouter.
     */
    public void addMove(Move move)
    {
        this.moves.add(this.numberOfMove++, move);
    }
    
    /**
     * @brief Fonction qui supprime le dernier coup de l'historique.
     * @return Retourne le dernier coup supprimé.
     */
    public Move deleteLastMove()
    {
        Move move;
        
        move = this.getLastMove();
        this.moves.remove(this.numberOfMove-1);
        --this.numberOfMove;
        return move;
    }
    
    /**
     * @brief Fonction qui supprime tout l'historique.
     */
    public void clean()
    {
        this.moves.clear();
        this.numberOfMove = 0;
    }
    
    /**
     * @brief Fonction qui donne tout les coups de l'historique, selon le
     * format du fichier de sauvegarde demander.
     * @return Retourne une chaine de caractère composé des coups de l'historique.
     */
    @Override
    public String toString()
    {
        String str = "";
        int count = 0;
                
        while ( count < this.numberOfMove )
        {
            str += ((Move)this.moves.get(count++)).toString();
            str += "\n";
        }
        return str;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                    Functions validation *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui vérifie si l'historique est vide ou non.
     * @return Retourne si la liste est vide ou non.
     */
    public boolean isEmpty()
    {
        return this.numberOfMove == 0;
    }
}
