package fr.campus.dungeoncrawler.characters.monsters;

public class Dragon extends Monster {
    /**
     * Crée un personnage de la classe Dragon
     * @param name Nom du personnage
     */
    public Dragon(String name) {
        super(name);
        this.health = 25;  // Réduit de 30 à 25
        this.maxHealth = 25;
        this.attack = 12;   // Réduit de 15 à 12
    }

    public Dragon(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.type = "Dragon";
    }

    public String getPlayerClass() {
        return "Dragon";
    }

}
