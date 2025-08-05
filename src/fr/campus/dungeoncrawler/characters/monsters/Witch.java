package fr.campus.dungeoncrawler.characters.monsters;

public class Witch extends Monster {
    /**
     * Crée un personnage de la classe Witch
     * @param name Nom du personnage
     */
    public Witch(String name) {
        super(name);
        this.health = 20;
        this.maxHealth = 20;
        this.attack = 10;
    }

    public Witch(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.type = "Witch";
    }

    public String getPlayerClass() {
        return "Sorcière";
    }

}