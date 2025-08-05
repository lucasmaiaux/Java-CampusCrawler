package fr.campus.dungeoncrawler.board.cells.offensive;

import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.characters.players.Warrior;
import fr.campus.dungeoncrawler.characters.players.Wizard;
import fr.campus.dungeoncrawler.equipments.offensive.Spell;

import static fr.campus.dungeoncrawler.db.ConnectMySQL.editHero;

public class CellSpell extends Cell {
    private Spell spell;

    public CellSpell(Spell spell) {
        this.type = "CellSpell";
        this.spell = spell;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void interact(Player player) {
        System.out.println("✨ Vous avez trouvé : " + spell.toString());

        if (player instanceof Wizard) {
            if (player.getOffensiveEquipment().getAttack() < spell.getAttack()) {
                System.out.println("Vous remplacez votre " + player.getOffensiveEquipment().toString());
                player.setOffensiveEquipment(spell);
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
        return "\n[Sort : " + spell.getName() + "]";
    }
}
