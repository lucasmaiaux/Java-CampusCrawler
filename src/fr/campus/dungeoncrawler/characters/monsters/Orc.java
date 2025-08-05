package fr.campus.dungeoncrawler.characters.monsters;

public class Orc extends Monster {
    /**
     * Crée un personnage de la classe Orc
     * @param name Nom du personnage
     */
    public Orc(String name) {
        super(name);
        this.health = 18;
        this.maxHealth = 18;
        this.attack = 9;
    }

    public Orc(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.type = "Orc";
    }

    public String getPlayerClass() {
        return "Orc";
    }

} 