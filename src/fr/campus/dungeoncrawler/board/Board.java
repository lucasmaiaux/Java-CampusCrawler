package fr.campus.dungeoncrawler.board;

import fr.campus.dungeoncrawler.board.cells.*;
import fr.campus.dungeoncrawler.board.cells.defensive.potion.CellPotion;
import fr.campus.dungeoncrawler.board.cells.defensive.shield.CellShield;
import fr.campus.dungeoncrawler.board.cells.empty.CellEmpty;
import fr.campus.dungeoncrawler.board.cells.enemies.CellEnemy;
import fr.campus.dungeoncrawler.board.cells.offensive.CellSpell;
import fr.campus.dungeoncrawler.board.cells.offensive.CellWeapon;
import fr.campus.dungeoncrawler.characters.monsters.Dragon;
import fr.campus.dungeoncrawler.characters.monsters.Goblin;
import fr.campus.dungeoncrawler.characters.monsters.Witch;
import fr.campus.dungeoncrawler.characters.players.Wizard;
import fr.campus.dungeoncrawler.equipments.defensive.Potion;
import fr.campus.dungeoncrawler.equipments.defensive.Shield;
import fr.campus.dungeoncrawler.equipments.offensive.Spell;
import fr.campus.dungeoncrawler.equipments.offensive.Weapon;

import java.util.ArrayList;
import java.util.List;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.createBoard;

public class Board {
    public int id;
    public List<Cell> cells;

    public Board() {
        this.cells = new ArrayList<Cell>();
    }

    public Board(List<Cell> cells) {
        this.cells = cells;
    }

    public Board(int id, List<Cell> cells) {
        this.id = id;
        this.cells = cells;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public void initBoard() {
        cells.add(new CellEmpty());

        cells.add(new CellEnemy(new Dragon("Dragon Rouge")));
        cells.add(new CellEnemy(new Witch("Magicienne Sombre")));
        cells.add(new CellEnemy(new Goblin("Gobelin Fourbe")));

        cells.add(new CellWeapon(new Weapon("Weapon", "Marteau du Campus Numerique", 15)));
        cells.add(new CellWeapon(new Weapon("Weapon", "Epée pourfendeuse de Mathys", 25)));

        cells.add(new CellSpell(new Spell("Spell", "Tempête d'éclairs", 12)));
        cells.add(new CellSpell(new Spell("Spell", "Boule de feu ultime", 50)));

        cells.add(new CellPotion(new Potion("Potion", "Potion de soin ULTIME", 40)));
        cells.add(new CellShield(new Shield("Shield", "Bouclier de fou FURIEUX", 80)));


        System.out.println("Creation d'un plateau de " + cells.size() + " cases");
    }

    public void initBoardRandom(int boardSize) {
        int cellsEnemy = 24;
        int cellsEnemyDragon = 4;
        int cellsEnemyWitch = 10;
        int cellsEnemyGoblin = 10;

        int cellsWeapon = 16;
        int cellsWeaponHammer = 5;
        int cellsWeaponSword = 4;
        int cellsSpellThunderstorm = 5;
        int cellsSpellFireball = 2;

        int cellsPotion = 6;
        int cellsShield = 2;

        for (int i = 0; i < boardSize; i++) {
            cells.add(new CellEmpty());
        }

        while (cellsEnemyDragon > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellEnemy(new Dragon("Dragon")));
                cellsEnemyDragon--;
            }
        }

        while (cellsEnemyWitch > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellEnemy(new Witch("Sorcière")));
                cellsEnemyWitch--;
            }
        }

        while (cellsEnemyGoblin > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellEnemy(new Goblin("Gobelin")));
                cellsEnemyGoblin--;
            }
        }

        while (cellsWeaponHammer > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellWeapon(new Weapon("Weapon", "Marteau magique", 15)));
                cellsWeaponHammer--;
            }
        }

        while (cellsWeaponSword > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellWeapon(new Weapon("Weapon", "Epée de glace", 25)));
                cellsWeaponSword--;
            }
        }

        while (cellsSpellThunderstorm > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellSpell(new Spell("Spell", "Tempête d'éclairs", 12)));
                cellsSpellThunderstorm--;
            }
        }

        while (cellsSpellFireball > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellSpell(new Spell("Spell", "Meteorite", 25)));
                cellsSpellFireball--;
            }
        }

        while (cellsPotion > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellPotion(new Potion("Potion", "Potion de soin elevée", 40)));
                cellsPotion--;
            }
        }

        while (cellsShield > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellShield(new Shield("Shield", "Bouclier en acier", 80)));
                cellsShield--;
            }
        }

        System.out.println("Creation d'un plateau de " + cells.size() + " cases");

    }

    @Override
    public String toString() {
        return "Plateau n°" + id + "\n" + cells;
    }
}
