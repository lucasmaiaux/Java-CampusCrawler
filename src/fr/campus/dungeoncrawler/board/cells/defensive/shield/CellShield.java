package fr.campus.dungeoncrawler.board.cells.defensive.shield;

import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.equipments.defensive.Shield;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.editHero;

public class CellShield extends Cell {
    private Shield shield;

    public CellShield(Shield shield) {
        this.type = "CellShield";
        this.shield = shield;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    @Override
    public void interact(Player player) {
        System.out.println("\uD83D\uDEE1\uFE0F Vous avez trouvé : " + shield.toString());

        if (player.getDefensiveEquipment().getDefense() < shield.getDefense()) {
            System.out.println("Vous remplacez votre " + player.getDefensiveEquipment().toString());
            player.setDefensiveEquipment(shield);
            editHero(player);
        }
        else {
            System.out.println("Vous gardez votre " + player.getDefensiveEquipment().toString());
        }
    }

    @Override
    public String toString() {
        return "\n[Shield]";
    }
}
