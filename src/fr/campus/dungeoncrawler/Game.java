package fr.campus.dungeoncrawler;
//import java.util.concurrent.TimeUnit;

public class Game {
    private int nb_players;
    private Character character;
    private int position;
    private Dice dice;

    public Game(int nb_players, Character character, int position) {
        this.nb_players = nb_players;
        this.character = character;
        this.position = position;
        this.dice = new Dice();
    }

    // Pour simuler un délai de $delay ms (1000 => 1s)
    public void customDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void runGame() {
        System.out.println("Début de la partie");

        // Boucle principale de jeu
        while (position < 64 && character.getHealth()>0) {
            newTurn();
        }
        endGame();
    }

    public void newTurn() {
        System.out.println("------------");
        System.out.println("Nouveau tour");

        int diceRoll = dice.newRoll();
        System.out.println("Lancer dé : " + diceRoll);

        System.out.print("Case " + position + " -> ");
        position = Math.min(position + diceRoll, 64);
        System.out.println(position);
        customDelay(1000);

    }

    public void endGame() {
        System.out.println("Partie terminée !");
    }
}
