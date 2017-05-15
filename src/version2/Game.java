package version2;

public class Game {
    
    private Historic historic;
    private Deck deck;
    private Player playerCurrent;
    private Player player2;
    /**
     * @brief Définit si c'est au tour du joueur 1 de jouer ou non
     */
    private boolean whoPlay;
    private int numberOfRound;
    
    public Game()
    {
        this.historic = new Historic();
        this.deck = new Deck(11);
        this.playerCurrent = new Player('b');
        this.player2 = new Player('w');
        this.whoPlay = true;
        this.numberOfRound = 0;
    }
    
    public Game(Player playerCurrent, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(11);
        this.playerCurrent = playerCurrent;
        this.player2 = player2;
        this.whoPlay = true;
        this.numberOfRound = 0;
        
        this.deck.createDeckC();
    }
    
    public Game(int size, Player playerCurrent, Player player2)
    {
        this.historic = new Historic();
        this.deck = new Deck(size);
        this.playerCurrent = playerCurrent;
        this.player2 = player2;
        this.whoPlay = true;
        this.numberOfRound = 0;
        
        this.deck.createDeckC();
    }
    
    public static int menu(int numberOfRound)
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
                menu = new String[5];
                menu[0] = "Jouer un coups\n";
                menu[1] = "Revenir au coup précédent\n";
                menu[2] = "Sauvegarder la partie\n";
                menu[3] = "Sauvegarder et quitter\n";
                menu[4] = "Quitter sans sauvegarder\n";
                break;
        }
        Interface.printMenu(menu);
        Interface.showMessage("Choix : ");
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
        menu[0] = "Jouer contre un joueur existant\n";
        menu[1] = "Jouer contre un nouveau joueur\n";
        menu[2] = "Jouer contre l'ordinateur\n";
        menu[3] = "Retour\n";
        Interface.printMenu(menu);
        Interface.showMessage("Choix : ");
        choice = Interface.getInt(1, menu.length);
        
        return choice;
    }
    
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
                    this.player2.clone(Player.getPlayerComputer());
                    break;
            }
            if ( this.playerCurrent.getColor() == 'w' )
                this.player2.setColor('b');
            else
                this.player2.setColor('w');
            
            this.numberOfRound = 1;
            this.deck.setSize(Deck.askSize());
            this.deck.createDeckC();
            this.play();
            this.deck.deleteDeckC();
        }
    }
    
    public void indexLoad()
    {
        Player oldPlayer;
        int numberMaxSaveguards, choice;
        
        oldPlayer = this.playerCurrent;
        Interface.showMessage("Voici la liste des sauvegarde.\n");
        Interface.showMessage(Saveguard.listSaveguard());
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
    
    public void play()
    {
        Move move;
        boolean leave, moveValid;

        leave = false;
        while ( !leave ) {
            this.showWhoPlay();
            this.showDeck();
            switch ( Game.menu(this.numberOfRound) )
            {
                case 1: // On joue
                    do
                    {
                        move = Move.askMove(this);
                        moveValid = this.playMove(move);
                        if ( !moveValid )
                            Interface.showMessage("Recommencez...\n");
                    } while ( !moveValid );
                    Interface.showMessage("Au joueur suivant de jouer...\n");
                    this.switchPlayer();
                    ++this.numberOfRound;
                    break;
                    
                case 3: // Sauvegarder
                    Interface.showMessage("Je sauvegarder la partie.\n");
                    this.save();
                    break;
                    
                case 4: // Sauvegarder et Quitter
                    this.save();
                    Interface.showMessage("Vous quitter votre partie.\n");
                    leave = true;
                    break;
                    
                case 5: // Quitter sans sauvegarder
                    Interface.showMessage("Vous Quitter votre partie sans enregistrer.\n");
                    leave = true;
                    break;
                    
                case 6: // échanger les couleur;
                    this.changeColor();
                    break;
                case 7: // Revenir au coup précédent
                    break;
                    
            }
        }
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
    
    public boolean getwhoPlay()
    {
        return this.whoPlay;
    }
    
    public int getNumberOfRound()
    {
        return this.numberOfRound;
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
    
    public void setwhoPlay(boolean whoPlay)
    {
        this.whoPlay = whoPlay;
    }
    
    public void setNumberOfRound(int numberOfRound)
    {
        this.numberOfRound = numberOfRound;
    }
    
    public void switchPlayer()
    {
        this.whoPlay = !this.whoPlay;
    }
    
    public void showDeck()
    {
        Interface.showMessage("Affichage du tablier : \n");
        this.deck.print();
    }
    
    public boolean playMove(Move move)
    {
        if ( move.play() )
        {
            this.historic.addMove(move);
            return true;
        }
        return false;
    }
    
    public void showWhoPlay()
    {
        String str, name;
        
        if ( this.whoPlay )
            name = this.playerCurrent.getPseudo();
        else
            name = this.player2.getPseudo();
        str = "C'est au tour de " + name + " de jouer."; 
        
    }
    
    public void save()
    {
        if ( Saveguard.addSaveguard(this) )
            Interface.showMessage("Fichier enregistrer.\n");
        else
            Interface.showMessage("Une erreur est survenu, la partie n'est pas sauvegarder.\n");
    }
}
