package fr.campus.dungeoncrawler.board;
//import java.util.concurrent.TimeUnit;

import fr.campus.dungeoncrawler.board.cells.*;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.exceptions.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int nbPlayers = 1;
    private int nbCells = 4;
    private Character character;
    private int playerPosition;
    //private Cell[] board = new Cell[4];
    private Board board = new Board();
    private Dice dice = new Dice();


    public Game(int nb_players, Character character, int position) {
        this.nbPlayers = nb_players;
        this.character = character;
        this.playerPosition = position;
        //this.dice = new Dice();
        //this.board = new ArrayList<Cell>();
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
        board.initBoard();

        // Boucle principale de jeu
        while (playerPosition < nbCells && character.getHealth()>0) {
            playTurn();
        }
        endGame();
    }

    public void playTurn() {
        System.out.println("------------");
        System.out.println("Nouveau tour");

        //int diceRoll = dice.newRoll();
        int diceRoll = dice.newFakeRoll();
        System.out.println("Lancer dé : " + diceRoll);

        try
        {
            //position = Math.min(position + diceRoll, 64);
            if (playerPosition + diceRoll > nbCells)
            {
                throw new OutOfBoardException();
            }
            else {
                // Affichage Case x -> x+y
                int playerNextPosition = playerPosition + diceRoll;
                System.out.print("Case " + (playerPosition) + " -> ");
                System.out.print(playerNextPosition);
                System.out.println(" " + board.cells.get(playerPosition).toString());
                playerPosition = playerNextPosition;

            }
        } catch (OutOfBoardException e) {
            System.out.println("Ligne d'arrivée dépassée, on relance");
        }

        customDelay(800);

    }

    public void endGame() {
        System.out.println("Partie terminée !");
    }
}
