package fr.campus.dungeoncrawler.equipments.defensive;

public class DefensiveEquipment {
    protected String type;
    protected String name;
    protected int defense;

    public DefensiveEquipment(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return name + " (" + defense + ")";
    }
}
