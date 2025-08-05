package fr.campus.dungeoncrawler.board.cells.empty;

import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.equipments.defensive.Potion;

public class CellEmpty extends Cell {

    public CellEmpty() {
        this.type = "CellEmpty";
    }

    @Override
    public void interact(Player player) {
        System.out.println("Rien ne se passe");
    }

    @Override
    public String toString() {
        return "\n[Vide]";
    }
}
