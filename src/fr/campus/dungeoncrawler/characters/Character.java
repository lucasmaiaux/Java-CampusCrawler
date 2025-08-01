package fr.campus.dungeoncrawler.characters;

import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;

public abstract class Character {
    protected int id;
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected OffensiveEquipment offensiveEquipment;
    protected DefensiveEquipment defensiveEquipment;

    public Character(String name) {
        this.name = name;
    }

    public Character(int id, String name, int health, int maxHealth, int attack) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
    }

    public Character(int id, String name, int health, int maxHealth, int attack, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public DefensiveEquipment getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    public abstract String getPlayerClass();


    @Override
    public String toString() {
        return "Personnage : " +
                name + " (" +
                this.getPlayerClass() + ")" +
                ", HP : " + health + " / " + maxHealth +
                ", Attack : " + attack +
                ", Off. Equip : " + offensiveEquipment.toString() +
                ", Def. Equip : " + defensiveEquipment.toString();
    }

}
