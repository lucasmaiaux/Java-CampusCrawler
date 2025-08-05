package fr.campus.dungeoncrawler.board.cells.enemies;

import fr.campus.dungeoncrawler.AnsiColors;
import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.board.dice.Dice20;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.monsters.Monster;
import fr.campus.dungeoncrawler.characters.players.Player;

import java.util.Scanner;

public class CellEnemy extends Cell {
    private Monster enemy;
    static Scanner clavier = new Scanner(System.in);
    private Dice20 dice20 = new Dice20();

    public CellEnemy(Monster enemy) {
        this.type = "CellEnemy";
        this.enemy = enemy;
    }

    public Character getEnemy() {
        return enemy;
    }

    public void setEnemy(Monster enemy) {
        this.enemy = enemy;
    }

    public void interact(Player player) {
        if (this.enemy.getHealth() <= 0) {
            System.out.println("Vous enjambez le cadavre d'un monstre mort");
        }
        else {
            fightEnemy(player);
        }
    }

    protected void fightEnemy(Player player) {
        int playerHealth = player.getHealth();
        int playerAttack = player.getAttack() + player.getOffensiveEquipment().getAttack();
        int enemyHealth = enemy.getHealth();
        int enemyAttack = enemy.getAttack();

        System.out.println("⚔️ Combat entre " + AnsiColors.GREEN_BRIGHT + player.getName() + AnsiColors.RESET + " et " + AnsiColors.RED_BRIGHT + enemy.getName() + AnsiColors.RESET);

        do {
            int playerAttackFinal = playerAttack;
            System.out.println(AnsiColors.CYAN_BRIGHT + "ATTAQUER [1] / FUIR [2]" + AnsiColors.RESET);

            int userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n

            switch (userChoice) {
                case 1:
                    int diceRoll = dice20.rollDice();

                    if (diceRoll == 20) {
                        // Réussite critique
                        System.out.println("Réussite critique ! (+2 Force)");
                        playerAttackFinal = playerAttackFinal + 2;
                    }
                    else if (diceRoll == 0) {
                        // Echec critique
                        System.out.println("Echec critique ! (Raté)");
                        playerAttackFinal = 0;
                    }

                    enemyHealth = enemyHealth - playerAttackFinal;
                    System.out.println("️⚔️ " + AnsiColors.GREEN_BRIGHT + player.getName() + AnsiColors.RESET + " tape et inflige " + AnsiColors.YELLOW_BRIGHT + playerAttackFinal + " DMG" + AnsiColors.RESET );

                    if (enemyHealth <= 0) {
                        enemy.setHealth(0);
                        System.out.println("\uD83D\uDC80 " + AnsiColors.RED_BRIGHT + enemy.getName() + AnsiColors.RESET + " meurt");
                    }
                    else {
                        enemy.setHealth(enemyHealth);

                        playerHealth = playerHealth - enemyAttack;
                        System.out.println("⚔️ " + AnsiColors.RED_BRIGHT  + enemy.getName() + AnsiColors.RESET + " tape et inflige " + AnsiColors.YELLOW_BRIGHT + enemy.getAttack() + " DMG" + AnsiColors.RESET);

                        if (playerHealth <= 0) {
                            player.setHealth(0);
                            System.out.println("\uD83D\uDC80 VOUS ETES MORT");
                            player.setDead(true);
                        }
                        else  {
                            player.setHealth(playerHealth);
                        }
                    }
                    break;
                case 2:
                    player.setFleeing(true);
                    System.out.println("Vous fuyez le combat !");
                    break;
                default:
                    System.out.println("Entrez un chiffre entre 1 et 2");
            }
        } while (enemyHealth >= 0 && !player.isFleeing() && !player.isDead());
    }

    public String toString() {
        return "\n[Ennemi : " + enemy.toString() + "]";
    }
}
