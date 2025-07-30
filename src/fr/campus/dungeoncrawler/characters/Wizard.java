package fr.campus.dungeoncrawler.characters;

import fr.campus.dungeoncrawler.equipments.offensive.Spell;


public class Wizard extends Character {

    public Wizard(String name, String type) {
        super(name, type);
        this.health = 6;
        this.attack = 15;
        this.offensiveEquipment = new Spell("Baton", "Spell", 1);;
    }
}
