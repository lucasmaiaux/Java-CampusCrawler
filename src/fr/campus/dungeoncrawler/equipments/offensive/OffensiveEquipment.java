package fr.campus.dungeoncrawler.equipments.offensive;

public abstract class OffensiveEquipment {
    protected String name;
    protected String type;
    protected int attack;

    public OffensiveEquipment(String name, String type, int attack) {
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
