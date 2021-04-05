/**
 * @HeadUrl https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/data/Monster.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved.
 */
package data;

import java.util.Date;
import java.util.Random;

/**
 * Encapsulation of the Monster data. The data is stored in the database and
 * represents one of the the player's Monster. 
 * 
 * @author $Author sis13 $
 */
public interface Monster {
    
    /**
     * Breeding class to breed new monsters
     * 
     * @param other Monster that is being bred with
     * @return Monster[] and array of the children
     */
    public Monster[] breeding(Monster other);

    public int getServerID();

    public void setServerID(int serverID);
    
    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);

    public Date getDob();

    public float getFertility();

    public void setFertility(float fertility);
    
    public String getUserID();

    public void setUserID(String userID);

    public Date getDod();

    public Double getBaseStrength();

    public void setBaseStrength(Double baseStrength);

    public Double getCurrentStrength();

    public void setCurrentStrength(Double currentStrength);

    public Double getBaseDefence();

    public void setBaseDefence(Double baseDefence);

    public Double getCurrentDefence();

    public void setCurrentDefence(Double currentDefence);

    public Double getBaseHealth();

    public void setBaseHealth(Double baseHealth);

    public Double getCurrentHealth();

    public void setCurrentHealth(Double currentHealth);

    public int getSaleOffer();

    public void setSaleOffer(int saleOffer);

    public int getBreedOffer();

    public void setBreedOffer(int breedOffer);

    public void setDob(Date dob);

    public void setDod(Date dod);

    public void updateStats(Double strength, Double defence, Double health);
    
    /**
     * Enrolls two Monsters in a epic battle. 
     * @param opponent The Monster this monster will fight versus.
     * @return The opponent is returned with new stats..
     */
    public double fight(Monster opponent);
}
