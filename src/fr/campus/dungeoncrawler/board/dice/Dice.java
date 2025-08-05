package fr.campus.dungeoncrawler.board.dice;

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

    /**
     * Throw a dice and return its result
     * @return Dice value (between 1 and 6)
     */
    public int newRoll(int faces) {
        return (int)(Math.random() * faces) + 1;
    }

    public int newFakeRoll() {
        return 1;
    }
}
