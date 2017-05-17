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
     * 
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
     * 
     * @param playerCurrent
     * @param player2 
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
     * 
     * @param size
     * @param playerCurrent
     * @param player2 
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
     * 
     * @param numberOfRound
     * @param historicBis
     * @return 
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
     * 
     * @return 
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
     * 
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
     * 
     */
    public void indexLoad()
    {
        Player oldPlayer;
        int numberMaxSaveguards, choice;
        
        oldPlayer = this.playerCurrent;
        Interface.showMessage("Voici la liste des sauvegarde.\n");
        Interface.showMessage(Saveguard.getListSaveguard());
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
     * 
     */
    public void play()
    {
        Move move;
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
     * 
     * @return 
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
     * 
     * @return 
     */
    public boolean save()
    {
        return Saveguard.addSaveguard(this);
    }
    
    /**
     * 
     * @return 
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
     * 
     * @return 
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
     * 
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
     * 
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
     * 
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
     * 
     * @return 
     */
    public Historic getHistoric()
    {
        return this.historic;
    }
    
    /**
     * 
     * @return 
     */
    public Deck getDeck()
    {
        return this.deck;
    }
    
    /**
     * 
     * @return 
     */
    public Player getPlayerCurrent()
    {
        return this.playerCurrent;
    }
    
    /**
     * 
     * @return 
     */
    public Player getPlayer2()
    {
        return this.player2;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getwhoPlay()
    {
        return this.whoPlay;
    }
    
    /**
     * 
     * @return 
     */
    public int getNumberOfRound()
    {
        return this.numberOfRound;
    }
    
    /**
     * 
     * @param historic 
     */
    public void setHistoric(Historic historic)
    {
        this.historic = historic;
    }
    
    /**
     * 
     * @param deck 
     */
    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }
    
    /**
     * 
     * @param player 
     */
    public void setPlayerCurrent(Player player)
    {
        this.playerCurrent = player;
    }
    
    /**
     * 
     * @param player 
     */
    public void setPlayer2(Player player)
    {
        this.player2 = player;
    }
    
    /**
     * 
     * @param whoPlay 
     */
    public void setwhoPlay(boolean whoPlay)
    {
        this.whoPlay = whoPlay;
    }
    
    /**
     * 
     * @param numberOfRound 
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
     * 
     */
    public void switchPlayer()
    {
        this.whoPlay = !this.whoPlay;
    }
    
    /**
     * 
     * @param move
     * @return 
     */
    public boolean playMove(Move move)
    {
        if ( !move.isValid() )
            return false;
        move.play();
        this.switchPlayer();
        this.historicBack.clean();
        return true;
    }
    
    /**
     * 
     * @param number
     * @return 
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
     * 
     * @param number
     * @return 
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
     * 
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
