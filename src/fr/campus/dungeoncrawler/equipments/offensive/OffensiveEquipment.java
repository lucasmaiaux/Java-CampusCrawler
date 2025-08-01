package fr.campus.dungeoncrawler.equipments.offensive;

public abstract class OffensiveEquipment {
    protected String type;
    protected String name;
    protected int attack;

    public OffensiveEquipment(String name, int attack) {
        this.name = name;
        this.attack = attack;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return name + " (" + attack + ")";
    }
}
