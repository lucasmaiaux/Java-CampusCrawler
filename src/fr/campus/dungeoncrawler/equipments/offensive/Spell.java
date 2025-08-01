package fr.campus.dungeoncrawler.equipments.offensive;

public class Spell extends OffensiveEquipment {

    public Spell(String name, int attack) {
        super(name, attack);
    }

    public Spell(String type, String name, int attack) {
        super(name, attack);
        this.type = type;
    }
}
