/**
 * @HeadUrl https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/data/Notification.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved.
 */

package data;
import java.util.Date;

/**
 * Encapsulation of data used to send notifications to Players who has gotten
 * request like friend/fight request, or has sold or breed a monster.
 * 
 * $Author sis13 $
 * 
 * @see Player
 * @see ServerCom.RemoteTalker
 * @see database.PersistenceManager
 */
public class Notification{
    /** Attributes */
    private int id;
    private String shortText;
    private String longText;
    private Player player;
    private Date timeSent;
    
    /**
     * 
     * @param shortText Short title.
     * @param longText Longer description.
     * @param player  Player to receive the notification.
     */
    public Notification(String shortText, String longText, Player player){
        this.id = 0;
        this.shortText = shortText;
        this.longText = longText;
        this.player = player;
        this.timeSent = new Date();
    }
    /**
     * 
     * @param shortText Short title.
     * @param longText Longer description.
     * @param player  Player to receive the notification.
     * @param timeSent Date object of the time when the notification was sent.
     */
    public Notification(int id, String shortText, String longText, Date timeSent){
        this.id = id;
        this.shortText = shortText;
        this.longText = longText;
        this.player = null;
        this.timeSent = timeSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }
    
    
}