package fr.campus.dungeoncrawler.equipments.defensive;

public class Shield extends DefensiveEquipment{

    public Shield(String name, int defense) {
        super(name, defense);
    }

    public Shield(String type, String name, int defense) {
        super(name, defense);
        this.type = type;
    }

}
