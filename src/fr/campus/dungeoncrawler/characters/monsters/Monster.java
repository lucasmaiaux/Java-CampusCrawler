package fr.campus.dungeoncrawler.characters.monsters;

import fr.campus.dungeoncrawler.characters.Character;

public abstract class Monster extends Character {

    protected String type;

    protected Monster(String name) {
        super(name);
    }

    protected Monster(int id, String name, int health, int maxHealth, int attack) {
        super(id, name, health, maxHealth, attack);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "(" + type + ") " + name + ", "+ health + "/" + maxHealth + " HP, ATK : " + attack;
    }

    public abstract String getPlayerClass();
}
