package fr.campus.dungeoncrawler.db;
import java.sql.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.campus.dungeoncrawler.RuntimeTypeAdapterFactory;
import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.characters.Warrior;
import fr.campus.dungeoncrawler.characters.Wizard;
import fr.campus.dungeoncrawler.equipments.defensive.DefensiveEquipment;
import fr.campus.dungeoncrawler.equipments.defensive.Potion;
import fr.campus.dungeoncrawler.equipments.defensive.Shield;
import fr.campus.dungeoncrawler.equipments.offensive.OffensiveEquipment;
import fr.campus.dungeoncrawler.equipments.offensive.Spell;
import fr.campus.dungeoncrawler.equipments.offensive.Weapon;

public class ConnectMySQL {
    public static void main(String args[])
    {
        //createHero();
        System.out.println(getHero(19).toString());
    }

    private static Gson factoryBuilder() {
        // Déserialisation des colonnes OffensiveEquipment et DefensiveEquipment (GSON)
        RuntimeTypeAdapterFactory<OffensiveEquipment> offensiveFactory =
                RuntimeTypeAdapterFactory.of(OffensiveEquipment.class, "type")
                        .registerSubtype(Spell.class, "Spell")
                        .registerSubtype(Weapon.class, "Weapon");

        RuntimeTypeAdapterFactory<DefensiveEquipment> defensiveFactory =
                RuntimeTypeAdapterFactory.of(DefensiveEquipment.class, "type")
                        .registerSubtype(Shield.class, "Shield")
                        .registerSubtype(Potion.class, "Potion");

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(offensiveFactory)
                .registerTypeAdapterFactory(defensiveFactory)
                .create();

        return gson;
    }

    public static void getHeroes() {
        Gson gson = factoryBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "MonPass123!");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Character_list");

            System.out.printf(
                    "%-3s | %-15s %-10s | %-9s | %-11s | %-15s | %-15s\n",
                    "ID", "Name", "Type", "Health", "Attack", "Off. Equip", "Def. Equip"
            );
            System.out.println("----------------------------------------------------------------------------------------------------");
            while(res.next()) {
                OffensiveEquipment offensiveEquipment = gson.fromJson(res.getString("OffensiveEquipment"), OffensiveEquipment.class);
                DefensiveEquipment defensiveEquipment = gson.fromJson(res.getString("DefensiveEquipment"), DefensiveEquipment.class);
                System.out.printf(
                        "%-3s | %-15s %-10s | HP: %-2s/%-2s | Attack: %-3s | %-15s | %-15s\n",
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

    public static Character getHero(int id) {
        int newId;
        String newName;
        String newType;
        int newHealth;
        int newMaxHealth;
        int newAttack;
        String newOffensiveEquipment;
        String newDefensiveEquipment;
        Character character = null;
        Gson gson = factoryBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "MonPass123!");
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
                        character = new Warrior(newId, newName, newHealth, newMaxHealth, newAttack, offensiveEquipment, defensiveEquipment);
                        break;
                    case "Wizard":
                        character = new Wizard(newId, newName, newHealth, newMaxHealth, newAttack, offensiveEquipment, defensiveEquipment);
                        break;
                }
            }
            conn.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
        return character;
    }

    public static Character createHero(Character hero) {
        Gson gson = factoryBuilder();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "MonPass123!");

            String sql = "INSERT INTO Character_list (Type, Name, Health, MaxHealth, Attack, OffensiveEquipment, DefensiveEquipment) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, hero.getPlayerClass());
            pstmt.setString(2, hero.getName());
            pstmt.setInt(3, hero.getHealth());
            pstmt.setInt(4, hero.getMaxHealth());
            pstmt.setInt(5, hero.getAttack());
            pstmt.setString(6, gson.toJson(hero.getOffensiveEquipment()));
            pstmt.setString(7, gson.toJson(hero.getDefensiveEquipment()));

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                hero.setId((int) id);
            }

            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return hero;
    }

    public static void editHero(Character hero) {
        Gson gson = factoryBuilder();
        int id = hero.getId();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "MonPass123!");

            String sql = "UPDATE Character_list SET Type = ?, Name = ?, Health = ?, MaxHealth = ?, Attack = ?, OffensiveEquipment = ?, DefensiveEquipment = ? WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, hero.getPlayerClass());
            pstmt.setString(2, hero.getName());
            pstmt.setInt(3, hero.getHealth());
            pstmt.setInt(4, hero.getMaxHealth());
            pstmt.setInt(5, hero.getAttack());
            pstmt.setString(6, gson.toJson(hero.getOffensiveEquipment()));
            pstmt.setString(7, gson.toJson(hero.getDefensiveEquipment()));
            pstmt.setInt(8, id);

            pstmt.executeUpdate();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void changeLifePoints(Character hero) {
        int id = hero.getId();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/campus_crawler", "root", "MonPass123!");

            String sql = "UPDATE Character_list SET Health = ? WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, hero.getHealth());
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
