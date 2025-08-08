package fr.campus.dungeoncrawler.equipments.offensive;

public class Weapon extends OffensiveEquipment {

    public Weapon(String name, int attack) {
        super(name, attack);
    }

    public Weapon(String type, String name, int attack) {
        super(name, attack);
        this.type = type;
    }

}
