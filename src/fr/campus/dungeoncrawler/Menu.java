package fr.campus.dungeoncrawler;

import fr.campus.dungeoncrawler.board.Game;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;
import fr.campus.dungeoncrawler.db.ConnectMySQL;

import java.util.Scanner;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.*;


/**
 * <p>The Menu class contains methods interacting with the user.</p>
 * <p>Using the Scanner class to intercept user inputs</p>
 */
public class Menu {
    static Scanner clavier = new Scanner(System.in);
    //static private Character character = null;

    /**
     * Start Menu
     */
    public static void startGame() {
        int userChoice = 0;

        do {
            System.out.println("""
                    =========================================
                    |           MENU PRINCIPAL              |
                    =========================================
                    | 1 - Créer un personnage               |
                    | 2 - Modifier un personnage            |
                    | 3 - Lancer une partie                 |
                    | 4 - Quitter                           |
                    =========================================""");
            //System.out.println("| 1 - Créer un personnage               |");
            //System.out.println("| 2 - Modifier un personnage            |");
            //System.out.println("| 3 - Lancer une partie                 |");
            //System.out.println("| 4 - Quitter                           |");

            userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n
            System.out.println("----------------------------------------");

            switch (userChoice) {
                case 1:
                    createCharacter();
                    break;
                case 2:
                    /*
                    if (character == null) {
                        System.out.println("Il faut d'abord créer un personnage !");
                    }
                    else {
                    */
                    editCharacters();

                    break;
                case 3:
                    /*
                    if (character == null) {
                        System.out.println("Il faut d'abord créer un personnage !");
                    }
                    else {
                    */
                    createGame();
                    //}
                    break;
                case 4:
                    // On quitte
                    break;
                default:
                    System.out.println("Entrez un chiffre entre 1 et 4");
            }

        } while (userChoice != 4);
    }

    public static void createCharacter() {
        System.out.println("-------- Creation de personnage --------");
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

    public static void editCharacters() {
        int userChoice = 0;
        System.out.println("------ Modification de personnage ------");
        System.out.println("Choisir un personnage depuis la liste : ");
        ConnectMySQL.getHeroes();

        userChoice = clavier.nextInt();
        clavier.nextLine(); // Pour clear le cache du \n

        editCharacter(getHero(userChoice));
    }

    public static void editCharacter(Character character) {
        int userChoice = 0;
        do {
            System.out.println(character.toString());
            System.out.println("1 - Modifier le nom");
            System.out.println("2 - Modifier la classe");
            System.out.println("3 - Menu principal");

            userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n

            switch (userChoice) {
                case 1:
                    editCharacterName(character);
                    break;
                case 2:
                    character = editCharacterType(character);
                    break;
                case 3:
                    // On quitte
                    break;
                default:
                    System.out.println("Entrez un chiffre entre 1 et 3");
            }
        } while (userChoice != 3);
    }

    public static void editCharacterName(Character character) {
        System.out.println("Nom actuel : " + character.getName());
        System.out.print("Nouveau nom : ");

        String newName = clavier.nextLine();
        character.setName(newName);
        editHero(character);
        System.out.println("Le nom a été correctement modifié");
    }

    public static Character editCharacterType(Character character) {
        String actualClass = character.getPlayerClass();
        System.out.println("Classe actuelle : " + actualClass);
        System.out.print("Nouvelle classe : ");

        String newClass = clavier.nextLine();

        if (newClass.equals(actualClass)) {
            System.out.println("La classe " + actualClass + " est déjà active!");
        }
        else {
            Character newCharacter;
            switch (newClass) {
                case "Warrior":
                    newCharacter = new Warrior(character.getId(), character.getName(), character.getHealth(), character.getMaxHealth(), character.getAttack());
                    editHero(newCharacter);
                    character = newCharacter;
                    break;
                case "Wizard":
                    newCharacter = new Wizard(character.getId(), character.getName(), character.getHealth(), character.getMaxHealth(), character.getAttack());
                    editHero(newCharacter);
                    character = newCharacter;
                    break;
            }
            System.out.println("La classe a été correctement modifiée");
        }
        return character;
    }

    public static void createGame() {
        int userChoice = 0;
        System.out.println("--------- Creation de la partie --------");
        System.out.println("Choisir un personnage depuis la liste : ");
        ConnectMySQL.getHeroes();

        userChoice = clavier.nextInt();
        clavier.nextLine(); // Pour clear le cache du \n

        Character character = getHero(userChoice);

        Game game = new Game(1, character, 0 );
        game.runGame();
    }
}
