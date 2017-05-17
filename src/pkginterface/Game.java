package pkginterface;

public class Game {
    
    private Historic historic;
    private Historic historicBack;
    private Deck deck;
    private Player playerCurrent;
    private Player player2;
    private boolean whoPlay;
    private int numberOfRound;
    
    /***************************************************************************
     *                                                                         *
     *                                                            Constructeur *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Constructeur par défaut.
     */
    public Game()
    {
        this.historic = new Historic();
        this.historicBack = new Historic();
        this.deck = new Deck(11);
        this.playerCurrent = new Player('b');
        this.player2 = new Player('w');
        this.whoPlay = true;
        this.numberOfRound = 0;
    }
    
    /**
     * @brief Constructeur
     * @param playerCurrent : Joueur courant qui lance le jeu.
     * @param player2  : Joueur secondaire avec qui le joueur courant joue.
     */
    public Game(Player playerCurrent, Player player2)
    {
        this.historic = new Historic();
        this.historicBack = new Historic();
        this.deck = new Deck(11);
        this.playerCurrent = playerCurrent;
        this.player2 = player2;
        this.whoPlay = true;
        this.numberOfRound = 0;
        
        this.deck.createDeckC();
    }
    
    /**
     * @brief Constructeur
     * @param size : Taille du tablier de jeu.
     * @param playerCurrent : Joueur courant qui lance le jeu.
     * @param player2  : Joueur secondaire avec qui le joueur courant joue.
     */
    public Game(int size, Player playerCurrent, Player player2)
    {
        this.historic = new Historic();
        this.historicBack = new Historic();
        this.deck = new Deck(size);
        this.playerCurrent = playerCurrent;
        this.player2 = player2;
        this.whoPlay = true;
        this.numberOfRound = 0;
        
        this.deck.createDeckC();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                   Functions Static menu *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui affiche le menu et demande à l'utilisateur
     * quel choix il souhaite.
     * @param numberOfRound : Numéro du tour, pour connaitre si le joueur
     * à le droit de revenir en arrière ou changer de couleur.
     * @param historicBis : Savoir si une demande de coup antérieur à été
     * effectuer.
     * @return Retourne le choix de l'utilsateur.
     */
    public static int menu(int numberOfRound, boolean historicBis)
    {
        String[] menu;
        int choice;
        
        switch (numberOfRound) {
            case 1:
                menu = new String[4];
                menu[0] = "Jouer un coups\n";
                menu[1] = "Sauvegarder la partie\n";
                menu[2] = "Sauvegarder et quitter\n";
                menu[3] = "Quitter sans sauvegarder\n";
                break;
            case 2:
                menu = new String[5];
                menu[0] = "Jouer un coups\n";
                menu[1] = "Echanger les couleur\n";
                menu[2] = "Sauvegarder la partie\n";
                menu[3] = "Sauvegarder et quitter\n";
                menu[4] = "Quitter sans sauvegarder\n";
                break;
            default:
                if (historicBis)
                {
                    menu = new String[6];
                    menu[0] = "Jouer un coups\n";
                    menu[1] = "Revenir au coup précédent\n";
                    menu[2] = "Revenir au coup suivant\n";
                    menu[3] = "Sauvegarder la partie\n";
                    menu[4] = "Sauvegarder et quitter\n";
                    menu[5] = "Quitter sans sauvegarder\n";
                }
                else
                {
                    menu = new String[5];
                    menu[0] = "Jouer un coups\n";
                    menu[1] = "Revenir au coup précédent\n";
                    menu[2] = "Sauvegarder la partie\n";
                    menu[3] = "Sauvegarder et quitter\n";
                    menu[4] = "Quitter sans sauvegarder\n";
                }
                break;
        }
        Interface.printMenu(menu);
        Interface.showMessage("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        switch ( numberOfRound )
        {
            case 1:
                switch ( choice )
                {
                    case 2:
                        return 3;
                    case 3:
                        return 4;
                    case 4:
                        return 5;
                    default:
                        break;
                }
                break;
            case 2:
                if ( choice == 2 )
                    return 6;
                break;
            default:
                if ( historicBis )
                {
                    switch (choice)
                    {
                        case 1:
                            return 1;
                        case 2:
                            return 7;
                        case 3:
                            return 8;
                        default:
                            return choice - 1;
                    }
                }
                else
                {
                    if ( choice == 2 )
                        return 7;
                }
                break;
        }
        return choice;
    }
    
    /**
     * @brief Affiche le menu secondaire pour savoir quel type de partie
     * il souhaite effectuer.
     * @return Retourne le choix de l'utilisateur. 
     */
    public static int menuOpponent()
    {
        String[] menu;
        int choice;
        
        menu = new String[4];
        menu[0] = "Jouer contre un joueur existant\n";
        menu[1] = "Jouer contre un nouveau joueur\n";
        menu[2] = "Jouer contre l'ordinateur\n";
        menu[3] = "Retour\n";
        Interface.printMenu(menu);
        Interface.showMessage("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        return choice;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                         Functions index *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction principale de la partie, qui permet de gérer la création
     * de partie. 
     */
    public void index()
    {
        Player player;
        int numberPlayers, choice;
        
        choice = Game.menuOpponent();
        
        if ( choice != 4 )
        {
            switch (choice) {
            // Jouer contre un joueur existant
                case 1:
                    do
                    {
                        Interface.showMessage(Player.listPlayer());
                        numberPlayers = Player.getNumberOfPlayers();
                        Interface.showMessage("Choix : ");
                        choice = Interface.getInt(1, numberPlayers);
                        player = Player.load(choice);
                        this.player2 = player;
                        if ( this.playerCurrent.equals(player) )
                            Interface.showMessage("\nVous ne pouvez vous selectionner.\n");
                    } while ( this.playerCurrent.equals(player));
                    break;
            // Jouer contre un nouveau joueur
                case 2:
                    player = Player.addPlayer();
                    this.player2 = player;
                    break;
            // Jouer contre l'ordinateur
                case 3:
                    this.player2.copy(Player.getPlayerComputer());
                    break;
            }
            if ( this.playerCurrent.getColor() == 'w' )
                this.player2.setColor('b');
            else
                this.player2.setColor('w');
            
            this.numberOfRound = 1;
            this.deck.askSize();
            this.deck.createDeckC();
            this.play();
            this.deck.deleteDeckC();
        }
    }
    
    /**
     * @brief Focntionprincipale de la partie, qui permet de charger une partie
     * sauvegarder.
     */
    public void indexLoad()
    {
        Player oldPlayer;
        int numberMaxSaveguards, choice;
        
        oldPlayer = this.playerCurrent;
        Interface.showMessage("Voici la liste des sauvegarde.\n");
        Interface.printMenu(Saveguard.getListSaveguard());
        numberMaxSaveguards = Saveguard.getNumberOfSaveguard();
        Interface.showMessage("0 : Retour\n");
        Interface.showMessage("Choix : ");
        choice = Interface.getInt(0, numberMaxSaveguards);
        if ( choice != 0 )
        {
            Interface.showMessage("Nous allons charger la partie "
                + choice + ".\n");
            if ( Saveguard.loadSaveguard(this, choice) )
            {
                this.play();
                this.deck.deleteDeckC();
            }
            else
            {
                Interface.showMessage("Fichier incorrect.\n");
            }
        }
        this.playerCurrent = oldPlayer;
    }
    
    /**
     * @brief Fonction qui permet de jouer à un jeu intialiser.
     */
    public void play()
    {
        boolean leave, winner;
        
        leave = false;
        winner = false;
        while ( !leave && !winner )
        {
            this.showWhoPlay();
            this.showDeck();
            switch ( Game.menu(this.numberOfRound, !this.historicBack.isEmpty()) )
            {
                case 1: // On joue
                    this.playMove();
                    break;
                    
                case 3: // Sauvegarder
                    Interface.showMessage("Je sauvegarder la partie.\n");
                    if ( this.save() )
                        Interface.showMessage("Partie enregistrer.\n");
                    else
                        Interface.showMessage("Partie non enregistrer.\n");
                    break;
                    
                case 4: // Sauvegarder et Quitter
                    if ( this.save() )
                    {
                        Interface.showMessage("Partie enregistrer.\n");
                        Interface.showMessage("Vous quitter votre partie.\n");
                        leave = true;
                    }
                    else
                    {
                        Interface.showMessage("Partie non enregistrer.\n");
                        Interface.showMessage("Vous ne quittez pas la partie.\n");
                    }
                    break;
                    
                case 5: // Quitter sans sauvegarder
                    Interface.showMessage("Vous Quitter votre partie sans enregistrer.\n");
                    leave = true;
                    break;
                    
                case 6: // échanger les couleur;
                    this.changeColor();
                    break;
                case 7: // Revenir au coup précédent
                    if ( this.goBack() )
                    {
                        Interface.showMessage("Vous êtes revenue en arrière.\n");
                    }
                    else
                    {
                        Interface.showMessage("Erreur aucun changement effectuer.\n");
                    }
                    break;
                case 8: // Revenir en avant
                    Interface.showMessage("On revient en avant.\n");
                    if ( this.goFront() )
                    {
                        Interface.showMessage("Vous êtes revenue en arrière.\n");
                    }
                    else
                    {
                        Interface.showMessage("Erreur aucun changement effectuer.\n");
                    }
                    break;
            }
        }
        
        if ( winner )
        {
            this.endGame();
        }
    }
    
    
    /***************************************************************************
     *                                                                         *
     *                                           Functions application console *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui demande à l'utilisateur de jouer un coup.
     * @return Retourne si le coup est correcte ou non.
     */
    public boolean playMove() // Case 1
    {
        Move move;
        
        move = Move.askMove(this);
        if ( !this.playMove(move) )
            return false;
        return true;
    }
    
    /**
     * @brief Fonction qui demande à la partie de se sauvegarder dans un
     * fichier.
     * @return Retourne si la partie à bien été enregistrer.
     */
    public boolean save()
    {
        return Saveguard.addSaveguard(this);
    }
    
    /**
     * @brief Fonction qui demande à l'utilisateur de combien de coup il veux
     * revenir en arrière et reviens en arrière du nombre de coup demander.
     * @return Retourne si le retour en arrière à bien été effectué.
     */
    public boolean goBack()
    {
        int nbMoveBack;
        
        Interface.showMessage("Tapez 0 pour annuler\n");
        Interface.showMessage("De combien voulez-vous revenir de coup en arrière ? ");
        nbMoveBack = Interface.getInt(0, this.historic.getNumberOfMove());
        
        if ( nbMoveBack != 0 )
        {
            if ( !this.goBackN(nbMoveBack) )
                return false;
        }
        return true;
    }
    
    /**
     * @brief Fonction qui demande à l'utilisateur de combien de coup il veux
     * revenir en avant et reviens en avant du nombre de coup demander.
     * @return Retourne si le retour en avant à bien été effectué.
     */
    public boolean goFront()
    {
        int nbMoveFront;
        
        Interface.showMessage("Tapez 0 pour annuler\n");
        Interface.showMessage("De combien voulez-vous revenir de coup en avant ? ");
        nbMoveFront = Interface.getInt(0, this.historic.getNumberOfMove());
        
        if ( nbMoveFront != 0 )
        {
            if ( !this.goFrontN(nbMoveFront) )
                return false;
        }
        return true;
    }
    
    /**
     * @brief Fonction qui met à jour les scores des joueurs en fonctions
     * du résulatat de la partie.
     */
    public void endGame()
    {
        char colorOfWinner;
        
        colorOfWinner = InterfaceJavaC.getWinner();
        if ( this.playerCurrent.getColor() == colorOfWinner )
        {
            this.playerCurrent.win();
            this.player2.loose();
        }
        else if ( this.player2.getColor() == colorOfWinner )
        {
            this.player2.win();
            this.playerCurrent.loose();
        }
        else
        {
            this.playerCurrent.noWinner();
            this.playerCurrent.noWinner();
        }
    }
    
    /***************************************************************************
     *                                                                         *
     *                                                       Fonctions display *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Fonction qui affiche quel utilisateur doit jouer.
     */
    public void showWhoPlay()
    {
        String str, name;
        
        if ( this.whoPlay )
            name = this.playerCurrent.getPseudo();
        else
            name = this.player2.getPseudo();
        str = "C'est au tour de " + name + " de jouer.\n"; 
        Interface.showMessage(str);
    }
    
    /**
     * @brief Fonction qui affiche le tablier de jeu.
     */
    public void showDeck()
    {
        Interface.showMessage("Affichage du tablier : \n");
        this.deck.print();
    }
    
    /***************************************************************************
     *                                                                         *
     *                                              Fonctions Getters, Setters *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Getter historique.
     * @return Retourne l'historique de la partie.
     */
    public Historic getHistoric()
    {
        return this.historic;
    }
    
    /**
     * @brief Getter tablier.
     * @return Retourne le tablier de jeu de la partie.
     */
    public Deck getDeck()
    {
        return this.deck;
    }
    
    /**
     * @brief Getter joueur courant(principale).
     * @return Retourne le joueur courant de la partie.
     */
    public Player getPlayerCurrent()
    {
        return this.playerCurrent;
    }
    
    /**
     * @brief Getter joueur secondaire.
     * @return Retourne le joueur secondaire.
     */
    public Player getPlayer2()
    {
        return this.player2;
    }
    
    /**
     * @biref Getter quel joueur doit jouer.
     * @return Retourne quel joueur c'est au tour de jouer.
     */
    public boolean getWhoPlay()
    {
        return this.whoPlay;
    }
    
    /**
     * @brief Getter nombre de tour de la partie.
     * @return Retourne le nombre de tour de la partie.
     */
    public int getNumberOfRound()
    {
        return this.numberOfRound;
    }
    
    /**
     * @brief Setter historique de la partie.
     * @param historic : Historique de la partie.
     */
    public void setHistoric(Historic historic)
    {
        this.historic = historic;
    }
    
    /**
     * @brief Setter tablier.
     * @param deck : Tablier de la partie.
     */
    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }
    
    /**
     * @brief Setter joueur courant.
     * @param player : Joueur de la partie.
     */
    public void setPlayerCurrent(Player player)
    {
        this.playerCurrent = player;
    }
    
    /**
     * @brief Setter joueur secondaire
     * @param player : Joueur secondaire de la partie.
     */
    public void setPlayer2(Player player)
    {
        this.player2 = player;
    }
    
    /**
     * @brief Setter quel joueur doit jouer.
     * @param whoPlay : Quel joueur doit jouer
     */
    public void setwhoPlay(boolean whoPlay)
    {
        this.whoPlay = whoPlay;
    }
    
    /**
     * @brief Setter nombre de tour.
     * @param numberOfRound : Nombre de tour de la partie.
     */
    public void setNumberOfRound(int numberOfRound)
    {
        this.numberOfRound = numberOfRound;
    }
    
    /***************************************************************************
     *                                                                         *
     *                                            Fonctions internal-managment *
     *                                                                         *
     **************************************************************************/
    
    /**
     * @brief Focntion qui inverse le joueur à qui de jouer.
     */
    public void switchPlayer()
    {
        this.whoPlay = !this.whoPlay;
    }
    
    /**
     * @brief Fonction qui joue un coup.
     * @param move : Coup à jouer.
     * @return Retourne si le coup à bien été jouer ou non.
     */
    public boolean playMove(Move move)
    {
        if ( !move.isValid() )
            return false;
        move.play();
        this.historic.addMove(move);
        this.switchPlayer();
        this.historicBack.clean();
        return true;
    }
    
    /**
     * @brief Fonction qui reviens en arrière en annulant des coups.
     * @param number : Nombre de coup à annuler.
     * @return Retourne si les coups on bien été annulés.
     */
    private boolean goBackN(int number)
    {
        Move move;
        int count;
        
        for ( count = 0; count < number; ++count )
        {
            move = this.historic.deleteLastMove();
            this.historicBack.addMove(move);
            Move.play('t', move.getCoordinates());
            this.switchPlayer();
        }
        return true;
    }
    
    /**
     * @brief Fonction qui reviens en avant en rejouant des coups joué.
     * @param number : Nombre de coup à rejouer.
     * @return Retourne si les coups on bien été rejoués.
     */
    private boolean goFrontN(int number)
    {
        Move move;
        int count;
        
        for ( count = 0; count < number; ++count )
        {
            move = this.historicBack.deleteLastMove();
            this.historic.addMove(move);
            if ( !move.isValid() )
                return false;
            move.play();
            this.switchPlayer();
        }
        return true;
    }
    
    /**
     * @brief Fonction qui inverse la couleur des deux joueurs. La modification
     * ne s'applique que sur la partie en cours.
     */
    public void changeColor()
    {
        if ( this.playerCurrent.getColor() == 'b' )
            this.playerCurrent.setColor('w');
        else
            this.playerCurrent.setColor('b');
        
        if ( this.player2.getColor() == 'b' )
            this.player2.setColor('w');
        else
            this.player2.setColor('b');
    }
}
