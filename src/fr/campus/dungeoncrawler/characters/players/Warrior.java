package fr.campus.dungeoncrawler.characters.players;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.defensive.Shield;
import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;
import fr.campus.dungeoncrawler.equipments.offensive.Weapon;

public class Warrior extends Player {
    /**
     * Crée un personnage de la classe Warrior
     * @param name Nom du personnage
     */
    public Warrior(String name) {
        super(name);
        this.health = 10;
        this.maxHealth = 10;
        this.attack = 10;
        this.offensiveEquipment = new Weapon("Weapon", "Sword", 1);
        this.defensiveEquipment = new Shield("Shield", "Wooden Shield", 2);
    }

    public Warrior(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.offensiveEquipment = new Weapon("Weapon", "Sword", 1);
        this.defensiveEquipment = new Shield("Shield", "Wooden Shield", 2);
    }

    public Warrior(int id, String name, int health, int maxHealth, int attack, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment) {
        super(id, name, health, maxHealth, attack, offensiveEquipment, defensiveEquipment);
    }

    public String getPlayerClass() {
        return "Warrior";
    }

}
