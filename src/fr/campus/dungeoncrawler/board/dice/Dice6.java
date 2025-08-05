package fr.campus.dungeoncrawler.board.dice;

public class Dice6 implements DiceInterface {
    private int faces = 6;

    public int getFaces() {
        return faces;
    }

    public void setFaces(int faces) {
        this.faces = faces;
    }

    @Override
    public int rollDice() {
        return (int)(Math.random() * faces) + 1;
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
