package fr.campus.dungeoncrawler.board.dice;

public class Dice20 implements DiceInterface {
    private int faces = 20;

    public int getFaces() {
        return faces;
    }

    public void setFaces(int faces) {
        this.faces = faces;
    }

    @Override
    public int rollDice() {
        int roll = (int)(Math.random() * faces) + 1;
        System.out.println("Lancer dé 20 : " + roll);
        return roll;
    }

    @Override
    public boolean isCritical() {
        return rollDice() == faces;
    }

    @Override
    public boolean isFailure() {
        return rollDice() == 0;
    }
}