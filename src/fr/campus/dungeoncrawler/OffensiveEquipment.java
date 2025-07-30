package fr.campus.dungeoncrawler;

public class OffensiveEquipment {
    private String name;
    private String type;
    private int attack;

    public OffensiveEquipment(String name,String type, int attack) {
        this.name = name;
        this.type = type;
        this.attack = attack;
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
        return attack;
    }

    public void setDefense(int defense) {
        this.attack = defense;
    }

    public String toString() {
        return "fr.campus.dungeoncrawler.DefensiveEquipment{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", defense=" + attack +
                '}';
    }
}
