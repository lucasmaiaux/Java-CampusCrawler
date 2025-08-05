package fr.campus.dungeoncrawler.characters.monsters;

public class Goblin extends Monster {
    /**
     * Crée un personnage de la classe Goblin
     * @param name Nom du personnage
     */
    public Goblin(String name) {
        super(name);
        this.health = 10;
        this.maxHealth = 10;
        this.attack = 5;
    }

    public Goblin(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.type = "Goblin";
    }

    public String getPlayerClass() {
        return "Goblin";
    }

}