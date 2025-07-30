package fr.campus.dungeoncrawler.characters;

import fr.campus.dungeoncrawler.equipments.offensive.Weapon;

public class Warrior extends Character {
    /**
     * Crée un personnage de la classe Warrior
     * @param name Nom du personnage
     * @param type Classe du personnage
     */
    public Warrior(String name, String type) {
        super(name, type);
        this.health = 10;
        this.attack = 10;
        this.offensiveEquipment = new Weapon("Epee", "Weapon", 1);
    }
}
