package fr.campus.dungeoncrawler.characters.players;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;

public abstract class Player extends Character {
    protected OffensiveEquipment offensiveEquipment;
    protected DefensiveEquipment defensiveEquipment;

    private boolean isFleeing = false;
    private boolean isDead = false;

    private int experience = 0;
    private int level = 1;
    private int experienceToNextLevel = 100;

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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public void setExperienceToNextLevel(int experienceToNextLevel) {
        this.experienceToNextLevel = experienceToNextLevel;
    }

    public void gainExperience(int exp) {
        this.experience += exp;
        checkLevelUp();
    }

    private void checkLevelUp() {
        if (experience >= experienceToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        experience -= experienceToNextLevel;
        experienceToNextLevel = level * 100; // Plus d'expérience nécessaire à chaque niveau
        
        // Amélioration des stats
        maxHealth += 2;
        health = maxHealth; // Soin complet lors du level up
        attack += 1;
        
        System.out.println("🎉 Niveau " + level + " ! Vos stats s'améliorent !");
        System.out.println("❤️ HP: " + health + "/" + maxHealth + " (+2) | ⚔️ ATK: " + attack + " (+1)");
    }

    public abstract String getPlayerClass();
}
