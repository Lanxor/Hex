package version2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable {
    
    private char color;
    private String pseudo;
    private String mail;
    private int yearOfBirth;
    
    public Player(char color)
    {
        this.color = color;
    }
    
    public Player(char color, String pseudo)
    {
        this.color = color;
        this.pseudo = pseudo;
    }
    
    public Player(char color, String pseudo, String mail)
    {
        this.color = color;
        this.pseudo = pseudo;
        this.mail = mail;
    }
    
    public Player(char color, String pseudo, String mail, int yearOfBirth)
    {
        this.color = color;
        this.pseudo = pseudo;
        this.mail = mail;
        this.yearOfBirth = yearOfBirth;
    }
    
    public static Player askNewPlayer()
    {
        char color;
        String pseudo;
        String mail;
        int yearOfBirth;
        Scanner sc;
        
        sc = new Scanner(System.in);
        System.out.println("Nouveau joueur.");
        System.out.print("Saississez un pseudonyme : ");
        pseudo = sc.nextLine();
        System.out.print("Saississez votre email : ");
        mail = sc.nextLine();
        System.out.print("Saississez votre année de naissance : ");
        yearOfBirth = Interface.getInt(1950, 2017);
        
        return new Player('w', pseudo, mail, yearOfBirth);
    }
    
    public static Game loadPlayer(Game game)
    {
        Player player1, player2;
        boolean isPossible;
        
        if ( Player.emptyPlayer() )
        {
            player1 = Player.askNewPlayer();
            player2 = Player.askNewPlayer();
            game.setPlayer1(player1);
            game.getPlayer1().setColor('b');
            game.setPlayer2(player2);
            game.getPlayer2().setColor('w');
            Player.addPlayer(player1);
            Player.addPlayer(player2);
        }
        else
        {
            do
            {
                System.out.println(Player.listPlayer());
                System.out.print("Choississez le premier joueur : ");
                game.setPlayer1(
                    Player.loadPlayer(
                        Interface.getInt(1, Player.getNumberOfPlayers())));
                System.out.println(Player.listPlayer());
                System.out.print("Choississez le second joueur : ");
                game.setPlayer2(
                    Player.loadPlayer(
                        Interface.getInt(1, Player.getNumberOfPlayers())));
                
                isPossible = game.getPlayer1().getColor() != game.getPlayer2().getColor();
                
                if ( !isPossible )
                {
                    System.out.println("Vous ne pouvez pas selectionner "
                            + "deux même joueur.");
                }
            } while ( !isPossible );
        }
        return game;
    }
    
    public static boolean addPlayer(Player player)
    {
        ObjectOutputStream oos;
        File file;
        String nameFilePlayer;
        
        nameFilePlayer = "../player/" + player.getPseudo() + ".player";
        file = new File(nameFilePlayer);
        if ( !file.exists() )
        {
            try {
                oos = new ObjectOutputStream(
                            new BufferedOutputStream(
                                new FileOutputStream(file)));

                oos.writeObject(player);
                oos.close();
            } catch (FileNotFoundException e) {}
            catch (IOException e) {}
            return true;
        }
        return false;
    }
    
    public boolean deletePlayer(Player player)
    {
        File file;
        String nameFilePlayer;
        
        nameFilePlayer = "../player/" + player.getPseudo() + ".player";
        file = new File(nameFilePlayer);
        if ( file.exists() )
        {
            file.delete();
            return true;
        }
        return false;
    }
    
    public static String listPlayer()
    {
        String str;
        ObjectInputStream ois;
        int count;
        File f;
        Player player;
        
        str = "";
        count = 1;
        f = new File("../player/");
        for ( File file : f.listFiles())
        {
            try {
                try {
                    ois = new ObjectInputStream(
                                new BufferedInputStream(
                                    new FileInputStream(file)));
                    try {
                        player = (Player)ois.readObject();
                        str += count++ + " : " + player.getPseudo()
                                + " (" + player.getColor() + ")\n";
                        ois.close();
                    } catch (ClassNotFoundException e) {}
                } catch (FileNotFoundException e) {}
                catch (IOException e) {}
            } catch (NullPointerException e) {}
        }
        return str;
    }
    
    public static Player loadPlayer(int number)
    {
        Player player;
        ObjectInputStream ois;
        int count;
        File folder;
        
        player = null;
        count = 1;
        folder = new File("../player/");
        for ( File file : folder.listFiles())
        {
            try {
                try {
                    ois = new ObjectInputStream(
                                new BufferedInputStream(
                                    new FileInputStream(file)));
                    try {
                        if ( count == number )
                        {
                          player = (Player)ois.readObject();  
                        }
                        ++count;
                        ois.close();
                    } catch (ClassNotFoundException e) {}
                } catch (FileNotFoundException e) {}
                catch (IOException e) {}
            } catch (NullPointerException e) {}
        }
        return player;
    }
    
    public Player[] getAllPlayers()
    {
        Player[] players;
        int count;
        ObjectInputStream ois;
        File f;
        
       
        players = new Player[this.getNumberOfPlayers()];
        count = 0;
        f = new File("player/");
        for ( File file : f.listFiles())
        {
            if ( !"default.player".equals(file.getName()) )
            {
                try {
                    try {
                        ois = new ObjectInputStream(
                                    new BufferedInputStream(
                                        new FileInputStream(file)));
                        try {
                            players[count++] = (Player)ois.readObject();
                        } catch (ClassNotFoundException e) {}
                    } catch (FileNotFoundException e) {}
                    catch (IOException e) {}
                } catch (NullPointerException e) {}
            }
        }
        return players;
    }
    
    public Player getPlayer(int number)
    {
        Player[] players;
        
        players = this.getAllPlayers();
        if ( number < players.length )
            return players[number];
        return null;
    }
    
    public static int getNumberOfPlayers()
    {
        File folder;
        
        folder = new File("../player/");
        return folder.listFiles().length;
    }
    
    public static boolean emptyPlayer()
    {
        return Player.getNumberOfPlayers() < 2;
    }
    
    public char getColor()
    {
        return this.color;
    }
    
    public String getPseudo()
    {
        return this.pseudo;
    }
    
    public String getMail()
    {
        return this.mail;
    }
    
    public int getYearOfBirth()
    {
        return this.yearOfBirth;
    }
    
    public void setColor(char color)
    {
        this.color = color;
    }
    
    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }
    
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    
    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }
    
    public String toString()
    {
        String str;
        str = this.color + " " + this.pseudo;
        return str;
    }
}
