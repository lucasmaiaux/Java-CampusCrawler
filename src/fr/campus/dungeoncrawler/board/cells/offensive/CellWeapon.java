package fr.campus.dungeoncrawler.board.cells.offensive;

import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.characters.players.Warrior;
import fr.campus.dungeoncrawler.equipments.offensive.Weapon;

import fr.campus.dungeoncrawler.db.ConnectMySQL;
import static fr.campus.dungeoncrawler.db.ConnectMySQL.*;

public class CellWeapon extends Cell {
    private Weapon weapon;

    public CellWeapon(Weapon weapon) {
        this.type = "CellWeapon";
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void interact(Player player) {
        System.out.println("\uD83D\uDDE1\uFE0F️ Vous avez trouvé : " + weapon.toString());

        if (player instanceof Warrior) {
            if (player.getOffensiveEquipment().getAttack() < weapon.getAttack()) {
                System.out.println("Vous remplacez votre " + player.getOffensiveEquipment().toString());
                player.setOffensiveEquipment(weapon);
                editHero(player);
            }
            else {
                System.out.println("Vous gardez votre " + player.getOffensiveEquipment().toString());
            }
        }
        else {
            System.out.println("Cette arme n'est pas pour votre classe");
        }

    }

    public String toString() {
        return "\n[Arme : " + weapon.getName() + "]";
    }
}
