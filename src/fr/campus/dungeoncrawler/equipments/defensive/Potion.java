package fr.campus.dungeoncrawler.equipments.defensive;

public class Potion extends DefensiveEquipment{

    public Potion(String name, int defense) {
        super(name, defense);
    }

    public Potion(String type, String name, int defense) {
        super(name, defense);
        this.type = type;
    }

}
