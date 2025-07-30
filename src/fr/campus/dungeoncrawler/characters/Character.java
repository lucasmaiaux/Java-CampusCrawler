package fr.campus.dungeoncrawler.characters;

import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;

public abstract class Character {
    protected String name;
    protected String type;
    protected int health;
    protected int attack;
    protected OffensiveEquipment offensiveEquipment;

    public Character(String name, String type) {
        this.name = name;
        this.type = type;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    @Override
    public String toString() {
        return "Personnage : " +
                name + " (" +
                type + ")" +
                ", HP : " + health +
                ", Attack : " + attack;
    }

}
