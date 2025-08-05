package fr.campus.dungeoncrawler.board.cells.defensive.shield;

import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.defensive.Shield;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.editHero;

public class CellDefensiveEquipment extends Cell {
    private DefensiveEquipment defensiveEquipment;

    public CellDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.type = "CellShield";
        this.defensiveEquipment = defensiveEquipment;
    }

    public DefensiveEquipment getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    @Override
    public void interact(Player player) {
        System.out.println("\uD83D\uDEE1\uFE0F Vous avez trouvé : " + defensiveEquipment.toString());

        if (player.getDefensiveEquipment().getDefense() < defensiveEquipment.getDefense()) {
            System.out.println("Vous remplacez votre " + player.getDefensiveEquipment().toString());
            player.setDefensiveEquipment(defensiveEquipment);
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
