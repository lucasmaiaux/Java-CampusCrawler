package fr.campus.dungeoncrawler;

public class Dice {
    private int value;

    public Dice() {
        // Initialisation à 0 ?
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "Dice{" +
                "value=" + value +
                '}';
    }

    public int newRoll() {
        return (int)(Math.random() * 6) + 1;
    }
}
