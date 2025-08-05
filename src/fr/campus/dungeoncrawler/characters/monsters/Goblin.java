package fr.campus.dungeoncrawler.characters.monsters;

public class Goblin extends Monster {
    /**
     * Crée un personnage de la classe Goblin
     * @param name Nom du personnage
     */
    public Goblin(String name) {
        super(name);
        this.health = 12;  // Augmenté de 10 à 12
        this.maxHealth = 12;
        this.attack = 7;    // Augmenté de 5 à 7
    }

    public Goblin(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.type = "Goblin";
    }

    public String getPlayerClass() {
        return "Goblin";
    }

}