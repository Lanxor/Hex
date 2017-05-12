
package pkginterface;

public class Game {
    
    private Historic historic;
    private Deck deck;
    private Player player1;
    private Player player2;
    /**
     * @brief Définit si c'est au tour du joueur 1 de jouer ou non
     */
    private boolean round;
    
    public Game(Player player1, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(11);
        this.player1 = player1;
        this.player2 = player2;
        this.round = true;
        
        this.deck.createDeckC();
    }
    
    public Game(int size, Player player1, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(size);
        this.player1 = player1;
        this.player2 = player2;
        this.round = true;
        
        this.deck.createDeckC();
    }
    
    public void gamePlay()
    {
        int round, choice;
        boolean leaveGame;
        
        round = 0;
        leaveGame = false;
        do
        {
            this.showDeck();
            Menu.choice(round);
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
                    this.saveGame();
                    System.out.println("Fichier enregistrer.");
                    break;
                    
                case 4: // Sauvegarder et Quitter
                    this.saveGame();
                    System.out.println("Fichier enregistrer.");
                    System.out.println("Vous quitter votre partie.");
                    leaveGame = true;
                    break;
                    
                case 5: // Quitter sans sauvegarder
                    System.out.println("Vous Quitter votre partie sans enregistrer.");
                    leaveGame = true;
                    break;
                    
            }
            System.out.println();
        } while ( !leaveGame );
        this.deck.deleteDeckC();
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
            playerCurrent = this.player1;
        else
            playerCurrent = this.player2;
        
        coordinates = Coordinates.askCoordinates(1, this.deck.getSize());
        move = new Move(playerCurrent, coordinates);
        
        if ( move.playMove() )
            this.historic.addMove(move);
        
        return false;
    }
    
    public Player getPlayer1()
    {
        return this.player1;
    }
    
    public Player getPlayer2()
    {
        return this.player2;
    }
    
    public Historic getHistoric()
    {
        return this.historic;
    }
    
    public Deck getDeck()
    {
        return this.deck;
    }
    
    public void showDeck()
    {
        System.out.println("Affichage du tablier : ");
        this.deck.show();
    }
    
    public void saveGame()
    {
        Saveguard.addSaveguard(this);
    }
    
    public static Game loadGame()
    {
        int numberMaxSaveguards, choice;
        Game game;
        
        System.out.println("Voici la liste des sauvegarde.");
        numberMaxSaveguards = Saveguard.listSaveguard();
        System.out.print("Choix : ");
        choice = Interface.getInt(1, numberMaxSaveguards);
        System.out.println("Nous allons charger la partie "
                + choice + ".");
        game = Saveguard.loadSaveguard(
                        Saveguard.getFileSaveguard(choice));
        
        return game;
    }
}
