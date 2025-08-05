package fr.campus.dungeoncrawler;

import fr.campus.dungeoncrawler.board.Board;
import fr.campus.dungeoncrawler.board.Game;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.characters.players.Warrior;
import fr.campus.dungeoncrawler.characters.players.Wizard;
import fr.campus.dungeoncrawler.db.ConnectMySQL;
import fr.campus.dungeoncrawler.AnsiColors;

import java.util.Scanner;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.*;


/**
 * <p>The Menu class contains methods interacting with the user.</p>
 * <p>Using the Scanner class to intercept user inputs</p>
 */
public class Menu {
    static Scanner clavier = new Scanner(System.in);

    /**
     * Start Menu
     */
    public static void startGame() {
        int userChoice = 0;

        do {
            System.out.println(
                    AnsiColors.WHITE_BRIGHT +
                            "=========================================\n" +
                            "|           " + AnsiColors.WHITE_BOLD_BRIGHT + "MENU PRINCIPAL" + AnsiColors.WHITE_BRIGHT + "              |\n" +
                            "=========================================\n" +
                            "| 1 - Créer un personnage               |\n" +
                            "| 2 - Modifier un personnage            |\n" +
                            "| 3 - Voir les plateaux                 |\n" +
                            "| 4 - Lancer une partie                 |\n" +
                            "| 5 - Quitter                           |\n" +
                            "=========================================" +
                            AnsiColors.RESET
            );

            userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n
            System.out.println(AnsiColors.WHITE_BRIGHT + "----------------------------------------------------");

            switch (userChoice) {
                case 1:
                    createPlayer();
                    break;
                case 2:
                    editPlayers();
                    break;
                case 3:
                    showBoards();
                    break;
                case 4:
                    createGame();
                    break;
                case 5:
                    // On quitte
                    break;
                default:
                    System.out.println("Entrez un chiffre entre 1 et 5");
            }

        } while (userChoice != 5);
    }

    public static void createPlayer() {
        System.out.println(AnsiColors.WHITE_BOLD_BRIGHT + "-------------- Creation de personnage --------------" + AnsiColors.RESET);
        String newClass;
        String newName;

        do {
            System.out.print("Type de personnage (Warrior/Wizard) : ");
            newClass = clavier.nextLine();
            if (!newClass.equals("Warrior") && !newClass.equals("Wizard")) {
                System.out.println("❌ Cette classe n'existe pas");
            }
        } while (!newClass.equals("Warrior") && !newClass.equals("Wizard"));

        System.out.print("Nom du personnage : ");
        newName = clavier.nextLine();

        switch (newClass) {
            case "Warrior":
                createHero(new Warrior(newName));
                break;
            case "Wizard":
                createHero(new Wizard(newName));
                break;
        }

        System.out.println("Personnage créé !");
    }

    public static void editPlayers() {
        int userChoice = 0;
        System.out.println(AnsiColors.WHITE_BOLD_BRIGHT + "------------ Modification de personnage ------------" + AnsiColors.RESET);
        getHeroes();
        System.out.println(AnsiColors.CYAN_BRIGHT + "Choisir un personnage depuis la liste : " + AnsiColors.RESET);

        userChoice = clavier.nextInt();
        clavier.nextLine(); // Pour clear le cache du \n

        editPlayer(getHero(userChoice));
    }

    public static void editPlayer(Player player) {
        int userChoice = 0;
        do {
            System.out.println(player.toString());
            System.out.println("1 - Modifier le nom");
            System.out.println("2 - Modifier la classe");
            System.out.println("3 - Menu principal");

            userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n

            switch (userChoice) {
                case 1:
                    editCharacterName(player);
                    break;
                case 2:
                    player = editCharacterType(player);
                    break;
                case 3:
                    // On quitte
                    break;
                default:
                    System.out.println("Entrez un chiffre entre 1 et 3");
            }
        } while (userChoice != 3);
    }

    public static void editCharacterName(Player player) {
        System.out.println("Nom actuel : " + player.getName());
        System.out.print("Nouveau nom : ");

        String newName = clavier.nextLine();
        player.setName(newName);
        editHero(player);
        System.out.println("Le nom a été correctement modifié");
    }

    public static Player editCharacterType(Player player) {
        String actualClass = player.getPlayerClass();
        System.out.println("Classe actuelle : " + actualClass);
        System.out.print("Nouvelle classe : ");

        String newClass = clavier.nextLine();

        if (newClass.equals(actualClass)) {
            System.out.println("La classe " + actualClass + " est déjà active!");
        }
        else {
            Player newPlayer;
            switch (newClass) {
                case "Warrior":
                    newPlayer = new Warrior(player.getId(), player.getName(), player.getHealth(), player.getMaxHealth(), player.getAttack());
                    editHero(newPlayer);
                    player = newPlayer;
                    break;
                case "Wizard":
                    newPlayer = new Wizard(player.getId(), player.getName(), player.getHealth(), player.getMaxHealth(), player.getAttack());
                    editHero(newPlayer);
                    player = newPlayer;
                    break;
            }
            System.out.println("La classe a été correctement modifiée");
        }
        return player;
    }

    public static void createGame() {
        int userChoice = 0;
        System.out.println(AnsiColors.WHITE_BOLD_BRIGHT + "--------------- Creation de la partie --------------" + AnsiColors.RESET);
        getHeroes();
        System.out.println(AnsiColors.CYAN_BRIGHT + "Choisir un personnage depuis la liste : " + AnsiColors.RESET);

        userChoice = clavier.nextInt();
        clavier.nextLine(); // Pour clear le cache du \n

        Player player = getHero(userChoice);

        getBoards();
        System.out.println(AnsiColors.CYAN_BRIGHT + "Choisir un ID de board (0 pour créer un nouveau) : " + AnsiColors.RESET);

        userChoice = clavier.nextInt();
        clavier.nextLine(); // Pour clear le cache du \n

        if  (userChoice == 0) {
            createNewGame(player);
        }
        else {
            continueGame(player, userChoice);
        }
    }

    public static void showBoards() {
        int userChoice = 0;
        System.out.println(AnsiColors.WHITE_BOLD_BRIGHT + "-------------- Visionneuse de plateau --------------" + AnsiColors.RESET);
        getBoards();
        System.out.println(AnsiColors.CYAN_BRIGHT + "Choisir un plateau depuis la liste : " + AnsiColors.RESET);

        userChoice = clavier.nextInt();
        clavier.nextLine(); // Pour clear le cache du \n

        System.out.println(getBoard(userChoice).toString());
    }

    public static void createNewGame(Player player) {
        Game game = new Game(1, player, 0 );
        game.runGame();
    }

    public static void continueGame(Player player, int id) {
        Board board = getBoard(id);
        Game game = new Game(1, player, 0 , board);
        game.runGame();
    }
}
