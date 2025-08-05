package fr.campus.dungeoncrawler.characters.monsters;

public class Dragon extends Monster {
    /**
     * Crée un personnage de la classe Dragon
     * @param name Nom du personnage
     */
    public Dragon(String name) {
        super(name);
        this.health = 30;
        this.maxHealth = 30;
        this.attack = 15;
    }

    public Dragon(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.type = "Dragon";
    }

    public String getPlayerClass() {
        return "Dragon";
    }

}
