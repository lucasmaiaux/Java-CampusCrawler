package fr.campus.dungeoncrawler.board.cells.defensive.potion;

import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.characters.players.Warrior;
import fr.campus.dungeoncrawler.equipments.defensive.Potion;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.editHero;

public class CellPotion extends Cell {
    private Potion potion;

    public CellPotion(Potion potion) {
        this.type = "CellPotion";
        this.potion = potion;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    @Override
    public void interact(Player player) {
        System.out.println("\uD83E\uDDEA Vous avez trouvé : " + potion.toString());

        if (player.getDefensiveEquipment().getDefense() < potion.getDefense()) {
            System.out.println("Vous remplacez votre " + player.getDefensiveEquipment().toString());
            player.setDefensiveEquipment(potion);
            editHero(player);
        }
        else {
            System.out.println("Vous gardez votre " + player.getDefensiveEquipment().toString());
        }
    }

    @Override
    public String toString() {
        return "\n[Potion]";
    }
}
