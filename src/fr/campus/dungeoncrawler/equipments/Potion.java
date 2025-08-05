package fr.campus.dungeoncrawler.equipments;

import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;

public class Potion extends DefensiveEquipment {

    public Potion(String name, int defense) {
        super(name, defense);
    }

    public Potion(String type, String name, int defense) {
        super(name, defense);
        this.type = type;
    }

}
