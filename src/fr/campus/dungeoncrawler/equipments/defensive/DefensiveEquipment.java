package fr.campus.dungeoncrawler.equipments.defensive;

public class DefensiveEquipment {
    protected String name;
    protected String type;
    protected int defense;

    public DefensiveEquipment(String name, String type, int defense) {
        this.name = name;
        this.type = type;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
