package fr.campus.dungeoncrawler.board;
//import java.util.concurrent.TimeUnit;

import fr.campus.dungeoncrawler.AnsiColors;
import fr.campus.dungeoncrawler.board.dice.Dice;
import fr.campus.dungeoncrawler.board.dice.Dice20;
import fr.campus.dungeoncrawler.board.dice.Dice6;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.exceptions.OutOfBoardException;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.createBoard;
import static fr.campus.dungeoncrawler.db.ConnectMySQL.editBoard;

public class Game {
    private int nbPlayers = 1;
    private Player player;
    private int playerPosition;
    //private Cell[] board = new Cell[4];
    private Board board;
    private Dice dice = new Dice();
    private Dice6 dice6 = new Dice6();

    public Game(int nb_players, Player player, int position) {
        this.nbPlayers = nb_players;
        this.player = player;
        this.playerPosition = position;
        this.board = new Board();
        //board.initBoardRandom(64);

        board.initBoardRandom(
                64,
                2,
                4,
                6,
                4,
                6,
                6,
                4,
                2,
                6,
                4,
                2,
                3,
                2,
                3,
                2,
                2,
                2,
                2);

        this.board = createBoard(board);
    }

    public Game(int nb_players, Player player, int position, Board board) {
        this.nbPlayers = nb_players;
        this.player = player;
        this.playerPosition = position;
        this.board = board;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    /**
     * Simule un délai de x ms (1000ms -> 1s)
     * @param delay délai en millisecondes
     */
    public void customDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void runGame() {
        System.out.println("Début de la partie");
        System.out.println("Plateau n°" + board.getId());
        //board.initBoardRandom(64);
        //createBoard(board);

        // Boucle principale de jeu
        while (playerPosition < (board.cells.size() - 1) && !player.isDead()) {
            playTurn();
        }
        endGame();
    }

    public void playTurn() {
        System.out.println("------------");
        System.out.println(AnsiColors.WHITE_BRIGHT + "Nouveau tour");
        
        // Affichage des stats du joueur
        System.out.println(AnsiColors.CYAN_BRIGHT + "📊 " + player.getName() + " - Niveau " + player.getLevel() + 
                         " | ❤️ " + player.getHealth() + "/" + player.getMaxHealth() + 
                         " | ⚔️ ATK: " + (player.getAttack() + player.getOffensiveEquipment().getAttack()) +
                         " | 🛡️ DEF: " + (player.getDefensiveEquipment() != null ? player.getDefensiveEquipment().getDefense() : 0) +
                         " | 📈 Exp: " + player.getExperience() + "/" + player.getExperienceToNextLevel() + AnsiColors.RESET);

        //int diceRoll = dice.newRoll(6);
        int diceRoll = dice6.rollDice();
        //int diceRoll = dice.newFakeRoll();
        System.out.println("🎲 Lancer dé : " + diceRoll);

        try
        {
            if (playerPosition + diceRoll > (board.cells.size() - 1))
            {
                throw new OutOfBoardException();
            }
            else {
                // Affichage Case x -> x+y
                int playerNextPosition = playerPosition + diceRoll;
                System.out.print("📍 Case " + playerPosition + " -> " + playerNextPosition);

                System.out.println(" " + board.cells.get(playerNextPosition).toString() + AnsiColors.RESET );
                board.cells.get(playerNextPosition).interact(player);

                if (player.isFleeing()) {
                    playerPosition = Math.max(0,playerPosition - dice.newRoll(6));
                    player.setFleeing(false);
                    System.out.println("🏃 Retour à la case " + playerPosition);
                }
                else {
                    playerPosition = playerNextPosition;
                }
            }
        } catch (OutOfBoardException e) {
            System.out.println("🎯 Ligne d'arrivée dépassée, on relance");
        }

        try{
            System.in.read();
        }
        catch(Exception e){}

        //customDelay(1000);

    }

    public void endGame() {
        if (player.isDead()) {
            System.out.println("Vous êtes mort comme un gros nul");
        }
        else {
            System.out.println("Partie terminée !");
        }

        editBoard(board);
    }
}
