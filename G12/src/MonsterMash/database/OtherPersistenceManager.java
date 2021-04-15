/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import data.FightRequest;
import data.Friend;
import data.Monster;
import data.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author sis13
 */
public class OtherPersistenceManager extends PersistenceManager {

    private final String dbname = "MonsterMash";
    private final String dbuser = "root";
    private final String dbpassword = "root";
    private final String dbhost = "localhost";
    private final String dbport = "1527";
    private Connection connection;
    private String error;
    private static OtherPersistenceManager onlyInstance;

    private OtherPersistenceManager() {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String connectionURL = "jdbc:derby://" + dbhost + ":" + dbport + "/" + dbname + ";create=true;user=" + dbuser + ";password=" + dbpassword;
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static OtherPersistenceManager getInstance() {
    	if(onlyInstance == null)
    		onlyInstance = new OtherPersistenceManager();
    	return onlyInstance;
    }

    public void acceptFriendRequest(Friend friend) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE \"Friendship\" SET \"confirmed\" = 'Y' WHERE \"id\" = '" + friend.getFriendshipID() + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            this.error = sqlExcept.getMessage();
        }
    }

    public Friend getFriend(String friendID) {
        Friend friend = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM \"Friendship\" WHERE \"id\" = '" + friendID + "'");
            results.next();

            friend = new Friend(results.getString("id"), results.getString("receiver_id"), results.getString("sender_id"), results.getInt("receiver_server_id"), results.getInt("sender_server_id"), results.getString("confirmed"));
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }

        return friend;
    }

    public void rejectFriend(Friend friend) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM \"Friendship\" WHERE \"id\" = '" + friend.getFriendshipID() + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            this.error = sqlExcept.getMessage();
        }
    }

    public void addFriend(Friend friend) {
        Statement stmt;
        try {
            String confirmed = "N";
            if(friend.isFriendshipConfirmed()){
                confirmed = "Y";
            }
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO \"Friendship\" (\"id\", \"sender_id\", \"receiver_id\", \"sender_server_id\", \"receiver_server_id\", \"confirmed\") VALUES ('"+
                friend.getFriendshipID()+"', '"+
                    friend.getLocalUserID()+"', '"+
                    friend.getRemoteUserID()+"', "+
                    friend.getLocalServerID()+", "+
                    friend.getRemoteServerID()+", '"+
                    confirmed+"')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            this.error = ex.getMessage();
        }
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Player\"");
            while (r.next()) {
                players.add(new Player(r.getString("id"), r.getString("username"), r.getString("password"), r.getInt("money"), this.getFriendList(r.getString("id")), this.getNotificationList(r.getString("id")), this.getMonsterList(r.getString("id")), r.getInt("server_id")));
            }
            r.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return players;
    }
    
    @Override
    public ArrayList<Monster> getMonsterList(String playerID){
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Monster\" WHERE \"user_id\" = '"+playerID+"'");
            while (r.next()) {
                monsters.add(new Monster(r.getString("id"),
                        r.getString("name"),
                        new Date(r.getLong("dob")),
                        new Date(r.getLong("dod")),
                        r.getDouble("base_strength"), 
                        r.getDouble("current_strength"),
                        r.getDouble("base_defence"),
                        r.getDouble("current_defence"),
                        r.getDouble("base_health"),
                        r.getDouble("current_health"), 
                        r.getFloat("fertility"),
                        r.getString("user_id"),
                        r.getInt("sale_offer"),
                        r.getInt("breed_offer")));
            }
            r.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return monsters;
    }

    public void storeFightRequest(FightRequest fr) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO \"Fight_request\" (\"id\", \"sender_id\", \"receiver_id\", \"sender_server_id\", \"receiver_server_id\", \"money\", \"receiver_monster_id\", \"sender_monster_id\") "
                    + "VALUES ('"+fr.getFightID()+"', '"+fr.getSenderID()+"', '"+fr.getRecieverID()+"', "+fr.getSenderServerID()+", "+fr.getRecieverServerID()+", "+fr.getMONEY()+", '"+fr.getReceiverMonsterID()+"', '"+fr.getSenderMonsterID()+"') ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            this.error = ex.getMessage();
        }
    }
    
    public Monster getMonster(String monsterID) {
        Monster monster = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Monster\" WHERE \"id\" = '" + monsterID + "'");
            r.next();
            monster = new Monster(r.getString("id"),
                        r.getString("name"),
                        new Date(r.getLong("dob")),
                        new Date(r.getLong("dod")),
                        r.getDouble("base_strength"), 
                        r.getDouble("current_strength"),
                        r.getDouble("base_defence"),
                        r.getDouble("current_defence"),
                        r.getDouble("base_health"),
                        r.getDouble("current_health"), 
                        r.getFloat("fertility"),
                        r.getString("user_id"),
                        r.getInt("sale_offer"),
                        r.getInt("breed_offer"));
            r.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println("asdasd"+sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return monster;
    }

    public FightRequest getFightRequest(String fightID) {
        FightRequest fr = null;
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Fight_request\" WHERE \"id\" = '" + fightID + "'");
            r.next();
            fr = new FightRequest(
                    r.getString("sender_id"),
                    r.getString("receiver_id"),
                    r.getString("id"),
                    r.getString("sender_monster_id"),
                    r.getString("receiver_monster_id"),
                    r.getInt("sender_server_id"),
                    r.getInt("receiver_server_id")
                    );
            
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        
        return fr;
    }

    public void updateMonster(Monster monster) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE \"Monster\" SET \"current_strength\" = "+monster.getCurrentStrength()
                    +", \"current_defence\" = "+monster.getCurrentDefence()
                    +", \"current_health\" = "+monster.getCurrentHealth()+" WHERE \"id\" = '"+monster.getId()+"'"
                        );
           
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
    }

    public void removeFightRequest(FightRequest fr) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM \"Fight_request\" WHERE \"id\" = '" + fr.getFightID() + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
    }
    
    public void updateMoney(Player player) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE \"Player\" SET \"money\" = "+player.getMoney()+" WHERE \"id\" = '"+player.getUserID()+"'");
           
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
    }

    public void removeMonster(String senderMonsterID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM \"Monster\" WHERE \"id\" = '" + senderMonsterID + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
    }

    public ArrayList<Friend> getFriends(Player player) {
        ArrayList<Friend> friends = new ArrayList<Friend>();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM \"Friendship\" WHERE \"receiver_id\" = '"+player.getUserID()+"' or \"sender_id\" = '"+player.getUserID()+"'");
            while (results.next()) {
            friends.add(new Friend(
                    results.getString("id"),
                    results.getString("receiver_id"),
                    results.getString("sender_id"),
                    results.getInt("receiver_server_id"),
                    results.getInt("sender_server_id"),
                    results.getString("confirmed")
                    ));
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        
        return friends;
     }
    
    public void removeUser(String userID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM \"Fight_request\" WHERE \"sender_id\" = '" + userID + "' OR \"receiver_id\" = '"+userID+"'");
            stmt.execute("DELETE FROM \"Friendship\" WHERE \"sender_id\" = '" + userID + "' OR \"receiver_id\" = '"+userID+"'");
            stmt.execute("DELETE FROM \"Monster\" WHERE \"user_id\" = '" + userID + "'");
            stmt.execute("DELETE FROM \"Notification\" WHERE \"player_id\" = '" + userID + "'");
            stmt.execute("DELETE FROM \"Player\" WHERE \"id\" = '" + userID + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
    }
    
    public Player getPlayerSafe(String userID) {
        Player selected = null;
        try{
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Player\" WHERE \"id\" = '"+userID+"'");
            if(r.next()){
                selected = new Player();
                selected.setMoney(r.getInt("money"));
                selected.setUserID((r.getString("id")));
                selected.setUsername(r.getString("username"));
            }else{
                return null;
            }
            r.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }

        return selected;
    }
}
