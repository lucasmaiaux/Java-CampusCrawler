package fr.campus.dungeoncrawler;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;

import java.util.Scanner;

/**
 * <p>The Menu class contains methods interacting with the user.</p>
 * <p>Using the Scanner class to intercept user inputs</p>
 */
public class Menu {
    static Scanner clavier = new Scanner(System.in);
    static private fr.campus.dungeoncrawler.characters.Character character = null;

    /**
     * Start Menu
     */
    public static void startGame() {
        int userChoice = 0;

        do {
            System.out.println("============ Menu principal ============");
            System.out.println("1 - Créer un personnage");
            System.out.println("2 - Modifier un personnage");
            System.out.println("3 - Lancer une partie");
            System.out.println("4 - Quitter");

            userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n
            System.out.println("----------------------------------------");

            switch (userChoice) {
                case 1:
                    createCharacter();
                    break;
                case 2:
                    if (character == null) {
                        System.out.println("Il faut d'abord créer un personnage !");
                    }
                    else {
                        editCharacter();
                    }
                    break;
                case 3:
                    if (character == null) {
                        System.out.println("Il faut d'abord créer un personnage !");
                    }
                    else {
                        createGame();
                    }
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
            System.out.print("Type de personnage (Guerrier/Magicien) : ");
            newClass = clavier.nextLine();
            if (!newClass.equals("Guerrier") && !newClass.equals("Magicien")) {
                System.out.println("❌ Cette classe n'existe pas");
            }
        } while (!newClass.equals("Guerrier") && !newClass.equals("Magicien"));

        System.out.print("Nom du personnage : ");
        newName = clavier.nextLine();

        System.out.println("Personnage créé !");

        switch (newClass) {
            case "Guerrier":
                character = new Warrior(newName, newClass);
                break;
            case "Magicien":
                character = new Wizard(newName, newClass);
                break;
        }
    }

    public static void editCharacter() {
        int userChoice = 0;
        do {
            System.out.println("------ Modification de personnage ------");
            System.out.println(character.toString());
            System.out.println("1 - Modifier le nom");
            System.out.println("2 - Modifier la classe");
            System.out.println("3 - Menu principal");


            userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n

            switch (userChoice) {
                case 1:
                    editCharacterName();
                    break;
                case 2:
                    editCharacterType();
                    break;
                case 3:
                    // On quitte
                    break;
                default:
                    System.out.println("Entrez un chiffre entre 1 et 3");
            }
        } while (userChoice != 3);
    }

    public static void editCharacterName() {
        System.out.println("Nom actuel : " + character.getName());
        System.out.print("Nouveau nom : ");

        String newName = clavier.nextLine();
        character.setName(newName);
        System.out.println("Le nom a été correctement modifié");
    }

    public static void editCharacterType() {
        System.out.println("Classe actuelle : " + character.getType());
        System.out.print("Nouvelle classe : ");

        String newClass = clavier.nextLine();
        character.setType(newClass);
        System.out.println("La classe a été correctement modifiée");
    }

    public static void createGame() {
        Game game = new Game(1, character, 1 );
        game.runGame();
    }
}
