package fr.campus.dungeoncrawler.board;

import fr.campus.dungeoncrawler.board.cells.*;
import fr.campus.dungeoncrawler.board.cells.defensive.potion.CellPotion;
import fr.campus.dungeoncrawler.board.cells.defensive.shield.CellDefensiveEquipment;
import fr.campus.dungeoncrawler.board.cells.empty.CellEmpty;
import fr.campus.dungeoncrawler.board.cells.enemies.CellEnemy;
import fr.campus.dungeoncrawler.board.cells.offensive.CellSpell;
import fr.campus.dungeoncrawler.board.cells.offensive.CellWeapon;
import fr.campus.dungeoncrawler.characters.monsters.Dragon;
import fr.campus.dungeoncrawler.characters.monsters.Goblin;
import fr.campus.dungeoncrawler.characters.monsters.Orc;
import fr.campus.dungeoncrawler.characters.monsters.Witch;
import fr.campus.dungeoncrawler.equipments.defensive.Armor;
import fr.campus.dungeoncrawler.equipments.Potion;
import fr.campus.dungeoncrawler.equipments.defensive.Helmet;
import fr.campus.dungeoncrawler.equipments.defensive.Shield;
import fr.campus.dungeoncrawler.equipments.offensive.Spell;
import fr.campus.dungeoncrawler.equipments.offensive.Weapon;

import java.util.ArrayList;
import java.util.List;

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
        cells.add(new CellDefensiveEquipment(new Shield("Shield", "Bouclier de fou FURIEUX", 80)));


        System.out.println("Creation d'un plateau de " + cells.size() + " cases");
    }

    public void initBoardRandom(
            int boardSize,
            int cellsEnemyDragon,
            int cellsEnemyWitch,
            int cellsEnemyGoblin,
            int cellsEnemyOrc,

            int cellsWeaponHammer,
            int cellsWeaponSword,
            int cellsWeaponAxe,
            int cellsWeaponLegendary,

            int cellsSpellThunderstorm,
            int cellsSpellFireball,
            int cellsSpellMeteor,

            int cellsShieldWood,
            int cellsShieldSteel,
            int cellsArmorLight,
            int cellsArmorHeavy,
            int cellsHelmetLeather,
            int cellsHelmetSteel,
            int cellsPotion)
    {

        int cellsTotal = cellsEnemyDragon + cellsEnemyWitch + cellsEnemyGoblin + cellsEnemyOrc +
                        cellsWeaponHammer + cellsWeaponSword + cellsWeaponAxe + cellsWeaponLegendary +
                        cellsSpellThunderstorm + cellsSpellFireball + cellsSpellMeteor +
                        cellsShieldWood + cellsShieldSteel +
                        cellsArmorLight + cellsArmorHeavy +
                        cellsHelmetLeather + cellsHelmetSteel +
                        cellsPotion;

        if (cellsTotal > (boardSize - 2)) {
            System.out.println("Pas assez de cases sur le plateau");
        }
        else {
            for (int i = 0; i < boardSize; i++) {
                cells.add(new CellEmpty());
            }

            // Placement des Dragons (boss rares)
            while (cellsEnemyDragon > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellEnemy(new Dragon("Dragon")));
                    cellsEnemyDragon--;
                }
            }

            // Placement des Sorcières
            while (cellsEnemyWitch > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellEnemy(new Witch("Sorcière")));
                    cellsEnemyWitch--;
                }
            }

            // Placement des Orcs
            while (cellsEnemyOrc > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellEnemy(new Orc("Orc")));
                    cellsEnemyOrc--;
                }
            }

            // Placement des Gobelins
            while (cellsEnemyGoblin > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellEnemy(new Goblin("Gobelin")));
                    cellsEnemyGoblin--;
                }
            }

            // Armes de base (Marteaux)
            while (cellsWeaponHammer > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellWeapon(new Weapon("Weapon", "Marteau de fer", 8)));
                    cellsWeaponHammer--;
                }
            }

            // Armes intermédiaires (Épées)
            while (cellsWeaponSword > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellWeapon(new Weapon("Weapon", "Épée d'acier", 12)));
                    cellsWeaponSword--;
                }
            }

            // Armes puissantes (Haches)
            while (cellsWeaponAxe > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellWeapon(new Weapon("Weapon", "Hache de guerre", 16)));
                    cellsWeaponAxe--;
                }
            }

            // Armes légendaires
            while (cellsWeaponLegendary > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellWeapon(new Weapon("Weapon", "Épée légendaire", 20)));
                    cellsWeaponLegendary--;
                }
            }

            // Sorts de base
            while (cellsSpellThunderstorm > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellSpell(new Spell("Spell", "Éclair", 8)));
                    cellsSpellThunderstorm--;
                }
            }

            // Sorts intermédiaires
            while (cellsSpellFireball > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellSpell(new Spell("Spell", "Boule de feu", 12)));
                    cellsSpellFireball--;
                }
            }

            // Sorts puissants
            while (cellsSpellMeteor > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellSpell(new Spell("Spell", "Météorite", 18)));
                    cellsSpellMeteor--;
                }
            }

            // Bouclier en bois
            while (cellsShieldWood > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellDefensiveEquipment(new Shield("Shield", "Bouclier en bois", 3)));
                    cellsShieldWood--;
                }
            }
            // Bouclier en acier
            while (cellsShieldSteel > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellDefensiveEquipment(new Shield("Shield", "Bouclier en acier", 6)));
                    cellsShieldSteel--;
                }
            }
            // Armure légère
            while (cellsArmorLight > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellDefensiveEquipment(new Armor("Armure légère", 4)));
                    cellsArmorLight--;
                }
            }
            // Armure lourde
            while (cellsArmorHeavy > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellDefensiveEquipment(new Armor("Armure lourde", 8)));
                    cellsArmorHeavy--;
                }
            }
            // Casque de cuir
            while (cellsHelmetLeather > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellDefensiveEquipment(new Helmet("Casque de cuir", 2)));
                    cellsHelmetLeather--;
                }
            }
            // Casque d'acier
            while (cellsHelmetSteel > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellDefensiveEquipment(new Helmet("Casque d'acier", 4)));
                    cellsHelmetSteel--;
                }
            }
            // Potions de soin (consommables)
            while (cellsPotion > 0) {
                int index = (int)(Math.random() * (boardSize - 2)) + 1;
                if (cells.get(index) instanceof CellEmpty) {
                    cells.set(index, new CellPotion(new Potion("Potion", "Potion de soin", 15)));
                    cellsPotion--;
                }
            }

            System.out.println("Creation d'un plateau de " + cells.size() + " cases");
        }

    }

    public void initBoardRandom(int boardSize) {
        // Réduction du nombre d'ennemis pour un meilleur équilibre
        int cellsEnemy = 16;  // Réduit de 24 à 16
        int cellsEnemyDragon = 2;   // Réduit de 4 à 2 (boss rare)
        int cellsEnemyWitch = 4;     // Réduit de 10 à 4
        int cellsEnemyGoblin = 6;    // Réduit de 10 à 6
        int cellsEnemyOrc = 4;       // Nouveau monstre intermédiaire

        // Plus d'équipements pour la progression
        int cellsWeapon = 18;        // Augmenté de 16 à 20
        int cellsWeaponHammer = 6;   // Arme de base
        int cellsWeaponSword = 6;    // Arme intermédiaire
        int cellsWeaponAxe = 4;      // Nouvelle arme puissante
        int cellsWeaponLegendary = 2; // Arme légendaire rare

        int cellsSpell = 12;         // Augmenté
        int cellsSpellThunderstorm = 6;  // Sort de base
        int cellsSpellFireball = 4;      // Sort intermédiaire
        int cellsSpellMeteor = 2;        // Sort puissant rare

        int cellsShieldWood = 3;    // Bouclier en bois (3 DEF)
        int cellsShieldSteel = 2;   // Bouclier en acier (6 DEF)
        int cellsArmorLight = 3;    // Armure légère (4 DEF)
        int cellsArmorHeavy = 2;    // Armure lourde (8 DEF)
        int cellsHelmetLeather = 2; // Casque de cuir (2 DEF)
        int cellsHelmetSteel = 2;   // Casque d'acier (4 DEF)
        int cellsPotion = 6;        // Potions de soin (consommables)

        for (int i = 0; i < boardSize; i++) {
            cells.add(new CellEmpty());
        }

        // Placement des Dragons (boss rares)
        while (cellsEnemyDragon > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellEnemy(new Dragon("Dragon")));
                cellsEnemyDragon--;
            }
        }

        // Placement des Sorcières
        while (cellsEnemyWitch > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellEnemy(new Witch("Sorcière")));
                cellsEnemyWitch--;
            }
        }

        // Placement des Orcs
        while (cellsEnemyOrc > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellEnemy(new Orc("Orc")));
                cellsEnemyOrc--;
            }
        }

        // Placement des Gobelins
        while (cellsEnemyGoblin > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellEnemy(new Goblin("Gobelin")));
                cellsEnemyGoblin--;
            }
        }

        // Armes de base (Marteaux)
        while (cellsWeaponHammer > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellWeapon(new Weapon("Weapon", "Marteau de fer", 8)));
                cellsWeaponHammer--;
            }
        }

        // Armes intermédiaires (Épées)
        while (cellsWeaponSword > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellWeapon(new Weapon("Weapon", "Épée d'acier", 12)));
                cellsWeaponSword--;
            }
        }

        // Armes puissantes (Haches)
        while (cellsWeaponAxe > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellWeapon(new Weapon("Weapon", "Hache de guerre", 16)));
                cellsWeaponAxe--;
            }
        }

        // Armes légendaires
        while (cellsWeaponLegendary > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellWeapon(new Weapon("Weapon", "Épée légendaire", 20)));
                cellsWeaponLegendary--;
            }
        }

        // Sorts de base
        while (cellsSpellThunderstorm > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellSpell(new Spell("Spell", "Éclair", 8)));
                cellsSpellThunderstorm--;
            }
        }

        // Sorts intermédiaires
        while (cellsSpellFireball > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellSpell(new Spell("Spell", "Boule de feu", 12)));
                cellsSpellFireball--;
            }
        }

        // Sorts puissants
        while (cellsSpellMeteor > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellSpell(new Spell("Spell", "Météorite", 18)));
                cellsSpellMeteor--;
            }
        }

        // Bouclier en bois
        while (cellsShieldWood > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellDefensiveEquipment(new Shield("Shield", "Bouclier en bois", 3)));
                cellsShieldWood--;
            }
        }
        // Bouclier en acier
        while (cellsShieldSteel > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellDefensiveEquipment(new Shield("Shield", "Bouclier en acier", 6)));
                cellsShieldSteel--;
            }
        }
        // Armure légère
        while (cellsArmorLight > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellDefensiveEquipment(new Armor("Armure légère", 4)));
                cellsArmorLight--;
            }
        }
        // Armure lourde
        while (cellsArmorHeavy > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellDefensiveEquipment(new Armor("Armure lourde", 8)));
                cellsArmorHeavy--;
            }
        }
        // Casque de cuir
        while (cellsHelmetLeather > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellDefensiveEquipment(new Helmet("Casque de cuir", 2)));
                cellsHelmetLeather--;
            }
        }
        // Casque d'acier
        while (cellsHelmetSteel > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellDefensiveEquipment(new Helmet("Casque d'acier", 4)));
                cellsHelmetSteel--;
            }
        }
        // Potions de soin (consommables)
        while (cellsPotion > 0) {
            int index = (int)(Math.random() * (boardSize - 2)) + 1;
            if (cells.get(index) instanceof CellEmpty) {
                cells.set(index, new CellPotion(new Potion("Potion", "Potion de soin", 15)));
                cellsPotion--;
            }
        }

        // Calcul du nombre d'éléments à placer (hors cases vides)
        int totalToPlace = cellsEnemyDragon + cellsEnemyWitch + cellsEnemyOrc + cellsEnemyGoblin
                + cellsWeaponHammer + cellsWeaponSword + cellsWeaponAxe + cellsWeaponLegendary
                + cellsSpellThunderstorm + cellsSpellFireball + cellsSpellMeteor
                + cellsShieldWood + cellsShieldSteel + cellsArmorLight + cellsArmorHeavy
                + cellsHelmetLeather + cellsHelmetSteel + cellsPotion;

        // Calcul dynamique du nombre de cases vides (10 à 15% du plateau)
        int minEmpty = (int)Math.ceil(boardSize * 0.10); // 10%
        int maxEmpty = (int)Math.floor(boardSize * 0.15); // 15%
        int emptyCells = minEmpty + (int)(Math.random() * (maxEmpty - minEmpty + 1));

        // Si trop d'éléments à placer, on réduit certains équipements aléatoirement
        while (totalToPlace > boardSize - emptyCells) {
            // On réduit d'abord les équipements les plus communs
            if (cellsWeaponHammer > 0) { cellsWeaponHammer--; totalToPlace--; continue; }
            if (cellsPotion > 0) { cellsPotion--; totalToPlace--; continue; }
            if (cellsSpellThunderstorm > 0) { cellsSpellThunderstorm--; totalToPlace--; continue; }
            if (cellsEnemyGoblin > 0) { cellsEnemyGoblin--; totalToPlace--; continue; }
            if (cellsShieldWood > 0) { cellsShieldWood--; totalToPlace--; continue; }
            // Sinon on réduit les autres
            if (cellsWeaponSword > 0) { cellsWeaponSword--; totalToPlace--; continue; }
            if (cellsArmorLight > 0) { cellsArmorLight--; totalToPlace--; continue; }
            if (cellsHelmetLeather > 0) { cellsHelmetLeather--; totalToPlace--; continue; }
            if (cellsEnemyOrc > 0) { cellsEnemyOrc--; totalToPlace--; continue; }
            if (cellsSpellFireball > 0) { cellsSpellFireball--; totalToPlace--; continue; }
            if (cellsShieldSteel > 0) { cellsShieldSteel--; totalToPlace--; continue; }
            if (cellsArmorHeavy > 0) { cellsArmorHeavy--; totalToPlace--; continue; }
            if (cellsHelmetSteel > 0) { cellsHelmetSteel--; totalToPlace--; continue; }
            if (cellsEnemyWitch > 0) { cellsEnemyWitch--; totalToPlace--; continue; }
            if (cellsSpellMeteor > 0) { cellsSpellMeteor--; totalToPlace--; continue; }
            if (cellsWeaponAxe > 0) { cellsWeaponAxe--; totalToPlace--; continue; }
            if (cellsWeaponLegendary > 0) { cellsWeaponLegendary--; totalToPlace--; continue; }
            if (cellsEnemyDragon > 0) { cellsEnemyDragon--; totalToPlace--; continue; }
            break;
        }

        System.out.println("Creation d'un plateau de " + cells.size() + " cases");

    }

    @Override
    public String toString() {
        return "Plateau n°" + id + "\n" + cells;
    }
}
