package fr.campus.dungeoncrawler.characters.players;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;

public abstract class Player extends Character {
    protected OffensiveEquipment offensiveEquipment;
    protected DefensiveEquipment defensiveEquipment;
    private boolean isFleeing = false;
    private boolean isDead = false;

    protected Player(String name) {
        super(name);
    }

    protected Player(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
    }

    protected Player(int id, String name, int health, int maxHealth, int attack, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment) {
        super(id, name, health, maxHealth, attack);
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
    }

    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    public DefensiveEquipment getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    public boolean isFleeing() {
        return isFleeing;
    }

    public void setFleeing(boolean fleeing) {
        isFleeing = fleeing;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public abstract String getPlayerClass();
}
