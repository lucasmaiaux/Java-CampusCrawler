package fr.campus.dungeoncrawler.db;
import java.sql.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.campus.dungeoncrawler.board.Board;
import fr.campus.dungeoncrawler.board.cells.Cell;
import fr.campus.dungeoncrawler.board.cells.defensive.potion.CellPotion;
import fr.campus.dungeoncrawler.board.cells.defensive.shield.CellDefensiveEquipment;
import fr.campus.dungeoncrawler.board.cells.empty.CellEmpty;
import fr.campus.dungeoncrawler.board.cells.enemies.CellEnemy;
import fr.campus.dungeoncrawler.board.cells.offensive.CellSpell;
import fr.campus.dungeoncrawler.board.cells.offensive.CellWeapon;
import fr.campus.dungeoncrawler.characters.monsters.*;
import fr.campus.dungeoncrawler.characters.players.Player;
import fr.campus.dungeoncrawler.characters.players.Warrior;
import fr.campus.dungeoncrawler.characters.players.Wizard;
import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.Potion;
import fr.campus.dungeoncrawler.equipments.defensive.Shield;
import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;
import fr.campus.dungeoncrawler.equipments.offensive.Spell;
import fr.campus.dungeoncrawler.equipments.offensive.Weapon;
import fr.campus.dungeoncrawler.equipments.defensive.Armor;
import fr.campus.dungeoncrawler.equipments.defensive.Helmet;

public class ConnectMySQL {
    public static void main(String args[])
    {
        //createHero();
        System.out.println(getBoard(10).toString());
    }

    private static Gson factoryEquipmentBuilder() {
        // Déserialisation des colonnes OffensiveEquipment et DefensiveEquipment (GSON)
        RuntimeTypeAdapterFactory<OffensiveEquipment> offensiveFactory =
                RuntimeTypeAdapterFactory.of(OffensiveEquipment.class, "type")
                        .registerSubtype(Spell.class, "Spell")
                        .registerSubtype(Weapon.class, "Weapon");

        RuntimeTypeAdapterFactory<DefensiveEquipment> defensiveFactory =
                RuntimeTypeAdapterFactory.of(DefensiveEquipment.class, "type")
                        .registerSubtype(Shield.class, "Shield")
                        .registerSubtype(Potion.class, "Potion")
                        .registerSubtype(Armor.class, "Armor")
                        .registerSubtype(Helmet.class, "Helmet");

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(offensiveFactory)
                .registerTypeAdapterFactory(defensiveFactory)
                .create();

        return gson;
    }

    private static Gson factoryBoardBuilder() {
        // Déserialisation de la colonne board
        RuntimeTypeAdapterFactory<Cell> boardFactory =
                RuntimeTypeAdapterFactory.of(Cell.class, "type")
                        .registerSubtype(CellPotion.class, "CellPotion")
                        .registerSubtype(CellDefensiveEquipment.class, "CellShield")
                        .registerSubtype(CellEmpty.class, "CellEmpty")
                        .registerSubtype(CellEnemy.class, "CellEnemy")
                        .registerSubtype(CellSpell.class, "CellSpell")
                        .registerSubtype(CellWeapon.class, "CellWeapon");

        RuntimeTypeAdapterFactory<Monster> monsterFactory =
                RuntimeTypeAdapterFactory.of(Monster.class, "type")
                        .registerSubtype(Dragon.class, "Dragon")
                        .registerSubtype(Goblin.class, "Goblin")
                        .registerSubtype(Witch.class, "Witch")
                        .registerSubtype(Orc.class, "Orc");

        RuntimeTypeAdapterFactory<OffensiveEquipment> offensiveFactory =
                RuntimeTypeAdapterFactory.of(OffensiveEquipment.class, "type")
                        .registerSubtype(Spell.class, "Spell")
                        .registerSubtype(Weapon.class, "Weapon");

        RuntimeTypeAdapterFactory<DefensiveEquipment> defensiveFactory =
                RuntimeTypeAdapterFactory.of(DefensiveEquipment.class, "type")
                        .registerSubtype(Shield.class, "Shield")
                        .registerSubtype(Potion.class, "Potion")
                        .registerSubtype(Armor.class, "Armor")
                        .registerSubtype(Helmet.class, "Helmet");



        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(boardFactory)
                .registerTypeAdapterFactory(monsterFactory)
                .registerTypeAdapterFactory(offensiveFactory)
                .registerTypeAdapterFactory(defensiveFactory)
                .create();

        return gson;
    }

    public static void getHeroes() {
        Gson gson = factoryEquipmentBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Character_list");

            System.out.printf(
                    "%-3s | %-15s %-10s | %-9s | %-11s | %-30s | %-30s\n",
                    "ID", "Name", "Type", "Health", "Attack", "Off. Equip", "Def. Equip"
            );
            System.out.println("----------------------------------------------------------------------------------------------------");
            while(res.next()) {
                OffensiveEquipment offensiveEquipment = gson.fromJson(res.getString("OffensiveEquipment"), OffensiveEquipment.class);
                DefensiveEquipment defensiveEquipment = gson.fromJson(res.getString("DefensiveEquipment"), DefensiveEquipment.class);
                System.out.printf(
                        "%-3s | %-15s %-10s | HP: %-2s/%-2s | Attack: %-3s | %-30s | %-30s\n",
                        res.getInt("Id"),
                        res.getString("Name"),
                        "(" + res.getString("Type") + ")",
                        res.getInt("Health"),
                        res.getInt("MaxHealth"),
                        res.getInt("Attack"),
                        offensiveEquipment.toString(),
                        defensiveEquipment.toString()
                );
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Player getHero(int id) {
        int newId;
        String newName;
        String newType;
        int newHealth;
        int newMaxHealth;
        int newAttack;
        String newOffensiveEquipment;
        String newDefensiveEquipment;
        Player player = null;
        Gson gson = factoryEquipmentBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Character_list WHERE Id = " + id);

            //System.out.println("---- Liste des personnages ----");
            if (res.next()) {
                newId = res.getInt("Id");
                newName = res.getString("Name");
                newType = res.getString("Type");
                newHealth = res.getInt("Health");
                newMaxHealth = res.getInt("MaxHealth");
                newAttack = res.getInt("Attack");
                OffensiveEquipment offensiveEquipment = gson.fromJson(res.getString("OffensiveEquipment"), OffensiveEquipment.class);
                DefensiveEquipment defensiveEquipment = gson.fromJson(res.getString("DefensiveEquipment"), DefensiveEquipment.class);

                switch (newType) {
                    case "Warrior":
                        player = new Warrior(newId, newName, newHealth, newMaxHealth, newAttack, offensiveEquipment, defensiveEquipment);
                        break;
                    case "Wizard":
                        player = new Wizard(newId, newName, newHealth, newMaxHealth, newAttack, offensiveEquipment, defensiveEquipment);
                        break;
                }
            }
            conn.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
        return player;
    }

    public static Player createHero(Player player) {
        Gson gson = factoryEquipmentBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");

            String sql = "INSERT INTO Character_list (Type, Name, Health, MaxHealth, Attack, OffensiveEquipment, DefensiveEquipment) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, player.getPlayerClass());
            pstmt.setString(2, player.getName());
            pstmt.setInt(3, player.getHealth());
            pstmt.setInt(4, player.getMaxHealth());
            pstmt.setInt(5, player.getAttack());
            pstmt.setString(6, gson.toJson(player.getOffensiveEquipment()));
            pstmt.setString(7, gson.toJson(player.getDefensiveEquipment()));

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                player.setId((int) id);
            }

            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return player;
    }

    public static void editHero(Player player) {
        Gson gson = factoryEquipmentBuilder();
        int id = player.getId();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");

            String sql = "UPDATE Character_list SET Type = ?, Name = ?, Health = ?, MaxHealth = ?, Attack = ?, OffensiveEquipment = ?, DefensiveEquipment = ? WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, player.getPlayerClass());
            pstmt.setString(2, player.getName());
            pstmt.setInt(3, player.getHealth());
            pstmt.setInt(4, player.getMaxHealth());
            pstmt.setInt(5, player.getAttack());
            pstmt.setString(6, gson.toJson(player.getOffensiveEquipment()));
            pstmt.setString(7, gson.toJson(player.getDefensiveEquipment()));
            pstmt.setInt(8, id);

            pstmt.executeUpdate();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Board createBoard(Board board) {
        Gson gson = factoryBoardBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");

            String sql = "INSERT INTO Boards (board) " +
                    "VALUES (?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            String json = gson.toJson(board);
            pstmt.setString(1, json);
            pstmt.executeUpdate();

            // Récupérer l'ID généré automatiquement
            ResultSet rs = pstmt.getGeneratedKeys();
            int generatedId = 0;
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
            board.setId(generatedId);
            String jsonWithId = gson.toJson(board);

            // Mettre à jour la ligne en base avec le bon JSON
            String updateSql = "UPDATE Boards SET board = ? WHERE id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, jsonWithId);
            updateStmt.setInt(2, generatedId);
            updateStmt.executeUpdate();

            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return board;
    }

    public static void editBoard(Board board) {
        Gson gson = factoryBoardBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");

            String sql = "UPDATE Boards SET board = ? WHERE Id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            String json = gson.toJson(board);
            pstmt.setString(1, json);
            pstmt.setInt(2, board.getId());

            pstmt.executeUpdate();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Board getBoard(int id) {
        int newId;
        Board board = null;
        Gson gson = factoryBoardBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Boards WHERE id = " + id);

            //System.out.println("---- Liste des personnages ----");
            if (res.next()) {
                newId = res.getInt("id");
                String newString = res.getString("board");
                //System.out.println(newString);
                board = gson.fromJson(res.getString("board"), Board.class);
                board.setId(newId);
            }
            conn.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
        return board;
    }

    public static void getBoards() {
        Gson gson = factoryBoardBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Boards");

            System.out.printf(
                    "%-3s | %-30s | %-30s\n",
                    "ID", "Created", "Updated"
            );
            System.out.println("-------------------------------------------------------------------");
            while(res.next()) {

                System.out.printf(
                        "%-3s | %-30s | %-30s\n",
                        res.getInt("id"),
                        res.getTimestamp("created_at"),
                        res.getTimestamp("updated_at")
                );
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void changeLifePoints(Player player) {
        int id = player.getId();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "");

            String sql = "UPDATE Character_list SET Health = ? WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, player.getHealth());
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
