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
        int playerDefense = player.getDefensiveEquipment() != null ? player.getDefensiveEquipment().getDefense() : 0;

        System.out.println("⚔️ Combat entre " + AnsiColors.GREEN_BRIGHT + player.getName() + AnsiColors.RESET + " et " + AnsiColors.RED_BRIGHT + enemy.getName() + AnsiColors.RESET);
        System.out.println(AnsiColors.CYAN_BRIGHT + "Vos stats: " + playerHealth + " HP, " + playerAttack + " ATK, " + playerDefense + " DEF" + AnsiColors.RESET);
        System.out.println(AnsiColors.RED_BRIGHT + "Ennemi: " + enemyHealth + " HP, " + enemyAttack + " ATK" + AnsiColors.RESET);

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
                        System.out.println(AnsiColors.YELLOW_BRIGHT + "🎯 Réussite critique ! (+3 Force)" + AnsiColors.RESET);
                        playerAttackFinal = playerAttackFinal + 3;
                    }
                    else if (diceRoll >= 15) {
                        // Réussite normale
                        System.out.println(AnsiColors.GREEN_BRIGHT + "✅ Bon coup ! (+1 Force)" + AnsiColors.RESET);
                        playerAttackFinal = playerAttackFinal + 1;
                    }
                    else if (diceRoll <= 3) {
                        // Échec critique
                        System.out.println(AnsiColors.RED_BRIGHT + "💥 Échec critique ! (Raté)" + AnsiColors.RESET);
                        playerAttackFinal = 0;
                    }
                    else if (diceRoll <= 8) {
                        // Échec partiel
                        System.out.println(AnsiColors.YELLOW_BRIGHT + "⚠️ Coup faible (-2 Force)" + AnsiColors.RESET);
                        playerAttackFinal = Math.max(0, playerAttackFinal - 2);
                    }

                    enemyHealth = enemyHealth - playerAttackFinal;
                    System.out.println("️⚔️ " + AnsiColors.GREEN_BRIGHT + player.getName() + AnsiColors.RESET + " tape et inflige " + AnsiColors.YELLOW_BRIGHT + playerAttackFinal + " DMG" + AnsiColors.RESET );

                    if (enemyHealth <= 0) {
                        enemy.setHealth(0);
                        System.out.println("\uD83D\uDC80 " + AnsiColors.RED_BRIGHT + enemy.getName() + AnsiColors.RESET + " meurt");
                        
                        // Attribution d'expérience basée sur le type d'ennemi
                        int expGained = 0;
                        switch (enemy.getPlayerClass()) {
                            case "Goblin":
                                expGained = 20;
                                break;
                            case "Orc":
                                expGained = 35;
                                break;
                            case "Sorcière":
                                expGained = 50;
                                break;
                            case "Dragon":
                                expGained = 100;
                                break;
                            default:
                                expGained = 25;
                        }
                        
                        player.gainExperience(expGained);
                        System.out.println(AnsiColors.GREEN_BRIGHT + "🎉 Victoire ! Vous gagnez " + expGained + " expérience !" + AnsiColors.RESET);
                        System.out.println(AnsiColors.CYAN_BRIGHT + "📊 Niveau " + player.getLevel() + " | Exp: " + player.getExperience() + "/" + player.getExperienceToNextLevel() + AnsiColors.RESET);
                    }
                    else {
                        enemy.setHealth(enemyHealth);

                        // Attaque de l'ennemi avec système d'esquive
                        int enemyDiceRoll = dice20.rollDice();
                        int enemyDamage = enemyAttack;
                        
                        if (enemyDiceRoll <= 5) {
                            System.out.println(AnsiColors.GREEN_BRIGHT + "🛡️ Vous esquivez l'attaque !" + AnsiColors.RESET);
                            enemyDamage = 0;
                        } else if (enemyDiceRoll >= 18) {
                            System.out.println(AnsiColors.RED_BRIGHT + "💥 Coup critique de l'ennemi !" + AnsiColors.RESET);
                            enemyDamage = enemyAttack + 3;
                        }

                        // Application de la défense
                        int finalDamage = Math.max(0, enemyDamage - playerDefense);
                        playerHealth = playerHealth - finalDamage;
                        
                        System.out.println("⚔️ " + AnsiColors.RED_BRIGHT  + enemy.getName() + AnsiColors.RESET + " tape et inflige " + AnsiColors.YELLOW_BRIGHT + finalDamage + " DMG" + AnsiColors.RESET);
                        if (playerDefense > 0 && enemyDamage > 0) {
                            System.out.println(AnsiColors.CYAN_BRIGHT + "🛡️ Votre défense a absorbé " + (enemyDamage - finalDamage) + " dégâts" + AnsiColors.RESET);
                        }

                        if (playerHealth <= 0) {
                            player.setHealth(0);
                            System.out.println("\uD83D\uDC80 " + AnsiColors.RED_BRIGHT + "VOUS ETES MORT" + AnsiColors.RESET);
                            player.setDead(true);
                        }
                        else  {
                            player.setHealth(playerHealth);
                            System.out.println(AnsiColors.GREEN_BRIGHT + "❤️ Il vous reste " + playerHealth + " HP" + AnsiColors.RESET);
                        }
                    }
                    break;
                case 2:
                    player.setFleeing(true);
                    System.out.println(AnsiColors.YELLOW_BRIGHT + "🏃 Vous fuyez le combat !" + AnsiColors.RESET);
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
