package version2;

public class Game {
    
    private Historic historic;
    private Deck deck;
    private Player playerCurrent;
    private Player player2;
    /**
     * @brief Définit si c'est au tour du joueur 1 de jouer ou non
     */
    private boolean round;
    
    public Game()
    {
        this.historic = new Historic();
        this.deck = new Deck(11);
        this.playerCurrent = new Player('b');
        this.player2 = new Player('w');
        this.round = true;
    }
    
    public Game(Player playerCurrent, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(11);
        this.playerCurrent = playerCurrent;
        this.player2 = player2;
        this.round = true;
        
        this.deck.createDeckC();
    }
    
    public Game(int size, Player playerCurrent, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(size);
        this.playerCurrent = playerCurrent;
        this.player2 = player2;
        this.round = true;
        
        this.deck.createDeckC();
    }
    
    public void initialize()
    {
        System.out.print("Saississez la taille du plateau : ");
        this.deck.setSize(Interface.getInt(1, 20));
        this.playerCurrent.setColor('b');
        this.player2.setColor('w');
        this.deck.createDeckC();
    }
    
    public Historic getHistoric()
    {
        return this.historic;
    }
    
    public Deck getDeck()
    {
        return this.deck;
    }
    
    public Player getPlayerCurrent()
    {
        return this.playerCurrent;
    }
    
    public Player getPlayer2()
    {
        return this.player2;
    }
    
    public boolean getRound()
    {
        return this.round;
    }
    
    public void setHistoric(Historic historic)
    {
        this.historic = historic;
    }
    
    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }
    
    public void setPlayerCurrent(Player player)
    {
        this.playerCurrent = player;
    }
    
    public void setPlayer2(Player player)
    {
        this.player2 = player;
    }
    
    public void setRound(boolean round)
    {
        this.round = round;
    }
    
    public void play()
    {
        int round, choice;
        boolean leave;
        
        round = 1;
        leave = false;
        do
        {
            this.showDeck();
            Game.menu(round);
            System.out.print("Choix : ");
            choice = Interface.getInt(1, 5);
            switch ( choice )
            {
                case 1: // On joue
                    while ( !this.playMove() )
                    {
                        System.out.println("Recommencez...");
                    }
                    System.out.println("Au joueur suivant de jouer...");
                    this.switchPlayer();
                    break;
                    
                case 3: // Sauvegarder
                    this.save();
                    System.out.println("Fichier enregistré.");
                    break;
                    
                case 4: // Sauvegarder et Quitter
                    this.save();
                    System.out.println("Fichier enregistrer.");
                    System.out.println("Vous quitter votre partie.");
                    leave = true;
                    break;
                    
                case 5: // Quitter sans sauvegarder
                    System.out.println("Vous Quitter votre partie sans enregistrer.");
                    leave = true;
                    break;
                    
            }
            System.out.println();
        } while ( !leave );
    }
    
    public void switchPlayer()
    {
        this.round = !this.round;
    }
    
    public boolean playMove()
    {
        Player playerCurrent;
        Coordinates coordinates;
        Move move;
        
        if ( this.round )
            playerCurrent = this.playerCurrent;
        else
            playerCurrent = this.player2;
        
        
        coordinates = Coordinates.askCoordinates(1, this.deck.getSize());
        move = new Move(playerCurrent, coordinates);
        
        if ( move.playMove() )
        {
            this.historic.addMove(move);
            return true;
        }
        return false;
    }
    
    public void showDeck()
    {
        System.out.println("Affichage du tablier : ");
        this.deck.print();
    }
    
    public void save()
    {
        Saveguard.addSaveguard(this);
    }
    
    public static Game load(Game game)
    {
        int numberMaxSaveguards, choice;
        
        System.out.println("Voici la liste des sauvegarde.");
        numberMaxSaveguards = Saveguard.listSaveguard();
        System.out.print("Choix : ");
        choice = Interface.getInt(1, numberMaxSaveguards);
        System.out.println("Nous allons charger la partie "
                + choice + ".");
        Saveguard.loadSaveguard(game, choice);
        System.out.println("Size : " + game.getDeck().getSize());
        
        return game;
    }
    
    public void deleteDeckC()
    {
        this.deck.deleteDeckC();
    }
    
    public static void menu(int numberOfRound)
    {
        String[] menu;
        
        switch (numberOfRound) {
            case 1:
                menu = new String[4];
                menu[0] = "Jouer un coups";
                menu[1] = "Sauvegarder la partie";
                menu[2] = "Sauvegarder et quitter";
                menu[3] = "Sauvegarder sans quitter";
                break;
            case 2:
                menu = new String[5];
                menu[0] = "Jouer un coups";
                menu[1] = "Echanger les couleur";
                menu[2] = "Sauvegarder la partie";
                menu[3] = "Sauvegarder et quitter";
                menu[4] = "Sauvegarder sans quitter";
                break;
            default:
                menu = new String[5];
                menu[0] = "Jouer un coups";
                menu[1] = "Revenir au coup précédent";
                menu[2] = "Sauvegarder la partie";
                menu[3] = "Sauvegarder et quitter";
                menu[4] = "Sauvegarder sans quitter";
                break;
        }
        Interface.printMenu(menu);
    }
}
