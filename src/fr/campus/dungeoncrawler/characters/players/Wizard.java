package fr.campus.dungeoncrawler.characters.players;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.defensive.Potion;
import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;
import fr.campus.dungeoncrawler.equipments.offensive.Spell;


public class Wizard extends Player {

    public Wizard(String name) {
        super(name);
        this.health = 6;
        this.maxHealth = 6;
        this.attack = 15;
        this.offensiveEquipment = new Spell("Spell", "Fireball", 1);
        this.defensiveEquipment = new Potion("Potion", "Health Potion", 2);
    }

    public Wizard(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
        this.offensiveEquipment = new Spell("Spell", "Fireball", 1);
        this.defensiveEquipment = new Potion("Potion", "Health Potion", 2);
    }

    public Wizard(int id, String name, int health, int maxHealth, int attack, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment) {
        super(id, name, health, maxHealth, attack, offensiveEquipment, defensiveEquipment);
    }

    public String getPlayerClass() {
        return "Wizard";
    }
}
