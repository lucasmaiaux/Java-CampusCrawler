package fr.campus.dungeoncrawler.board.cells;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.players.Player;

public abstract class Cell {
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract String toString();
    public abstract void interact(Player player);
}