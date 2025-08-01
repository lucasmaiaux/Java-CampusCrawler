package fr.campus.dungeoncrawler.board;

import fr.campus.dungeoncrawler.board.cells.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<Cell> cells = new ArrayList<Cell>();

    public void initBoard() {
        cells.add(new CellEmpty());
        cells.add(new CellEnemy());
        cells.add(new CellWeapon());
        cells.add(new CellLootbox());
        System.out.println("Creation d'un plateau de " + cells.size() + " cases");
    }
}
