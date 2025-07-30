package fr.campus.dungeoncrawler;

public class DefensiveEquipment {
    private String name;
    private String type;
    private int defense;

    public DefensiveEquipment(String name,String type, int defense) {
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

    public String toString() {
        return "fr.campus.dungeoncrawler.DefensiveEquipment{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", defense=" + defense +
                '}';
    }
}
