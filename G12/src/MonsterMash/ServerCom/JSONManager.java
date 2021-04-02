/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCom;

import data.Monster;
import data.Player;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sis13
 */
public class JSONManager {

    /**
     *
     * @param player
     * @return
     */
    public static JSONObject jsonPlayer(Player player) {
        JSONObject jObj = new JSONObject();

        try {
            jObj.put("money", player.getMoney());
            jObj.put("userID", player.getUserID());
            jObj.put("name", player.getUsername());
        } catch (JSONException ex) {
            Logger.getLogger(JSONManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jObj;
    }

    public static JSONObject jsonMonster(Monster monster) {
        JSONObject jObj = new JSONObject();

        try {
            jObj.put("monsterID", String.valueOf(monster.getId()));
            jObj.put("userID", String.valueOf(monster.getUserID()));
            jObj.put("baseStrength", monster.getBaseStrength());
            jObj.put("currentStrength", monster.getCurrentStrength());
            jObj.put("baseDefence", monster.getBaseDefence());
            jObj.put("currentDefence", monster.getCurrentDefence());
            jObj.put("baseHealth", monster.getBaseHealth());
            jObj.put("currentHealth", monster.getCurrentHealth());
            jObj.put("birthDate", monster.getDob().getTime());
            jObj.put("lifespan", monster.getDod().getTime()-monster.getDob().getTime());
            jObj.put("saleOffer", monster.getSaleOffer());
            jObj.put("breedOffer", monster.getBreedOffer());
        } catch (JSONException ex) {
            Logger.getLogger(JSONManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jObj;
    }

    public static JSONArray jsonMonsterList(ArrayList<Monster> monsters) {
        JSONArray ja = new JSONArray();
        
        for(Monster m: monsters) {
            ja.put(jsonMonster(m));
        }
        
        return ja;
    }
    
    public static String jsonUsers(ArrayList<Player> players) {
       JSONArray ja = new JSONArray();
        
        for(Player p: players) {
            ja.put(jsonPlayer(p));
        }
        
        return ja.toString(); 
    }
}
