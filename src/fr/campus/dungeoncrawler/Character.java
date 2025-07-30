package fr.campus.dungeoncrawler;

public class Character {
    private String name;
    private String type;
    private int health;
    private int attack;
    private OffensiveEquipment OffensiveEquipment;

    public Character(String name, String type) {
        this.name = name;
        this.type = type;

        switch (type) {

            case "Guerrier":
                this.health = 10;
                this.attack = 10;
                this.OffensiveEquipment = new OffensiveEquipment("Epee", "Weapon", 1);
                break;
            case "Magicien":
                this.health = 6;
                this.attack = 15;
                this.OffensiveEquipment = new OffensiveEquipment("Baton", "Spell", 1);;
                break;
        }

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
        return OffensiveEquipment;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        OffensiveEquipment = offensiveEquipment;
    }

    public String toString() {
        return "fr.campus.dungeoncrawler.Character{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", fr.campus.dungeoncrawler.OffensiveEquipment=" + OffensiveEquipment +
                '}';
    }
}
