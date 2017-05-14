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
    
    public static int menu(int numberOfRound)
    {
        String[] menu;
        int choice;
        
        switch (numberOfRound) {
            case 1:
                menu = new String[4];
                menu[0] = "Jouer un coups";
                menu[1] = "Sauvegarder la partie";
                menu[2] = "Sauvegarder et quitter";
                menu[3] = "Quitter sans sauvegarder";
                break;
            case 2:
                menu = new String[5];
                menu[0] = "Jouer un coups";
                menu[1] = "Echanger les couleur";
                menu[2] = "Sauvegarder la partie";
                menu[3] = "Sauvegarder et quitter";
                menu[4] = "Quitter sans sauvegarder";
                break;
            default:
                menu = new String[5];
                menu[0] = "Jouer un coups";
                menu[1] = "Revenir au coup précédent";
                menu[2] = "Sauvegarder la partie";
                menu[3] = "Sauvegarder et quitter";
                menu[4] = "Quitter sans sauvegarder";
                break;
        }
        Interface.printMenu(menu);
        System.out.print("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        switch (numberOfRound)
        {
            case 1:
                switch (choice)
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
                if ( choice == 2 )
                    return 7;
                break;
        }
        return choice;
    }
    
    public static int menuOpponent()
    {
        String[] menu;
        int choice;
        
        menu = new String[4];
        menu[0] = "Jouer contre un joueur existant";
        menu[1] = "Jouer contre un nouveau joueur";
        menu[2] = "Jouer contre l'ordinateur";
        menu[3] = "Retour";
        Interface.printMenu(menu);
        System.out.print("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        return choice;
    }
    
    public void index()
    {
        Player player;
        int numberPlayers, choice;
        char color;
        
        choice = Game.menuOpponent();
        
        if ( choice != 4 )
        {
            switch (choice) {
            // Jouer contre un joueur existant
                case 1:
                    do
                    {
                        System.out.println(Player.listPlayer());
                        numberPlayers = Player.getNumberOfPlayers();
                        choice = Interface.getInt(1, numberPlayers);
                        player = Player.load(choice);
                        this.player2 = player;
                        if ( this.playerCurrent.equals(player) )
                            System.out.println("Vous ne pouvez vous selectionner.");
                    } while ( this.playerCurrent.equals(player));
                    break;
            // Jouer contre un nouveau joueur
                case 2:
                    player = Player.addPlayer();
                    break;
            // Jouer contre l'ordinateur
                case 3:
                    this.player2.setPseudo("Ordinateur");
                    this.player2.setMail("Thomas.olivier3@yahoo.fr");
                    this.player2.setYearOfBirth(2017);
                    break;
            }
            if ( this.playerCurrent.getColor() == 'w' )
                this.player2.setColor('b');
            else
                this.player2.setColor('w');
            this.play();
        }
    }
    
    public void indexLoad()
    {
        int numberMaxSaveguards, choice;
        
        System.out.println("Voici la liste des sauvegarde.");
        numberMaxSaveguards = Saveguard.listSaveguard();
        System.out.print("Choix : ");
        choice = Interface.getInt(1, numberMaxSaveguards);
        System.out.println("Nous allons charger la partie "
                + choice + ".");
        Saveguard.loadSaveguard(this, choice);
        System.out.println("Size : " + this.getDeck().getSize());
    }
    
    public void play()
    {
        int round;
        boolean leave;
        
        this.deck.createDeckC();
        round = 1;
        leave = false;
        while ( !leave ) {
            this.showDeck();
            switch ( Game.menu(round) )
            {
                case 1: // On joue
                    System.out.println("On joue");
                    while ( !this.playMove() )
                    {
                        System.out.println("Recommencez...");
                    }
                    System.out.println("Au joueur suivant de jouer...");
                    this.switchPlayer();
                    ++round;
                    break;
                    
                case 3: // Sauvegarder
                    System.out.println("Je sauvegarder la partie.");
                    this.save();
                    break;
                    
                case 4: // Sauvegarder et Quitter
                    this.save();
                    System.out.println("Vous quitter votre partie.");
                    leave = true;
                    break;
                    
                case 5: // Quitter sans sauvegarder
                    System.out.println("Vous Quitter votre partie sans enregistrer.");
                    leave = true;
                    break;
                    
                case 6: // échanger les couleur;
                    this.changeColor();
                    break;
                case 7: // Revenir au coup précédent
                    break;
                    
            }
        }
        this.deck.deleteDeckC();
    }
    
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
        if ( Saveguard.addSaveguard(this) )
            System.out.println("Fichier enregistrer.");
        else
            System.out.println("Une erreur est survenu, la partie n'est pas sauvegarder.");
    }
}
