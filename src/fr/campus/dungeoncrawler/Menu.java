package fr.campus.dungeoncrawler;

import java.util.Scanner;

public class Menu {
    static Scanner clavier = new Scanner(System.in);
    static private Character character = null;

    public static void startMenu() {
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
                    newCharacterMenu();
                    break;
                case 2:
                    editCharacterMenu();
                    break;
                case 3:
                    if (character == null) {
                        System.out.println("Il faut d'abord créer un personnage !");
                    }
                    else {
                        newGame();
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

    public static void newCharacterMenu() {
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
        character = new Character(newName, newClass);
    }

    public static void editCharacterMenu() {
        int userChoice = 0;
        do {
            System.out.println("------ Modification de personnage ------");
            System.out.println("Personnage actuel : " + character.getName() + " (" + character.getType() + ")");
            System.out.println("1 - Modifier le nom");
            System.out.println("2 - Modifier la classe");
            System.out.println("3 - Menu principal");


            userChoice = clavier.nextInt();
            clavier.nextLine(); // Pour clear le cache du \n

            switch (userChoice) {
                case 1:
                    editCharacterNameMenu();
                    break;
                case 2:
                    editCharacterTypeMenu();
                    break;
                case 3:
                    // On quitte
                    break;
                default:
                    System.out.println("Entrez un chiffre entre 1 et 3");
            }
        } while (userChoice != 3);
    }

    public static void editCharacterNameMenu() {
        System.out.println("Nom actuel : " + character.getName());
        System.out.print("Nouveau nom : ");

        String newName = clavier.nextLine();
        character.setName(newName);
        System.out.println("Le nom a été correctement modifié");
    }

    public static void editCharacterTypeMenu() {
        System.out.println("Classe actuelle : " + character.getType());
        System.out.print("Nouvelle classe : ");

        String newClass = clavier.nextLine();
        character.setType(newClass);
        System.out.println("La classe a été correctement modifiée");
    }

    public static void newGame() {
        Game game = new Game(1, character, 1 );
        game.runGame();
    }
}
