/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Llionv
 */
public class NotificationTest {

    public NotificationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Notification.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Player player = new Player();
        Notification notification = new Notification("jau1@aber.ac.uk", "password", player);
        assertEquals("Id should be 0", 0, notification.getId());

    }

    /**
     * Test of setId method, of class Notification.
     */
    @Test
    public void testSetId() {
        System.out.println("getId");
        Player player = new Player();

        Notification notification = new Notification("short message", "long message text", player);
        notification.setId(1);
        assertEquals("Id should be 1", 1, notification.getId());
    }

    /**
     * Test of getShortText method, of class Notification.
     */
    @Test
    public void testGetShortText() {
        System.out.println("getShortText");
        Player player = new Player();

        Notification notification = new Notification("short message", "long message text", player);

        assertEquals("It should be 'short message'", "short message", notification.getShortText());
    }

    /**
     * Test of setShortText method, of class Notification.
     */
    @Test
    public void testSetShortText() {
        System.out.println("setShortText");
        Player player = new Player();

        Notification notification = new Notification("short message", "long message text", player);
        notification.setShortText("new short message");
        assertEquals("It should be 'new short message'", "new short message", notification.getShortText());
    }

    /**
     * Test of getLongText method, of class Notification.
     */
    @Test
    public void testGetLongText() {
        System.out.println("getLongText");
        Player player = new Player();
        Notification notification = new Notification("short message", "long message text", player);
        assertEquals("It should be 'long message text'", "long message text", notification.getLongText());
    }

    /**
     * Test of setLongText method, of class Notification.
     */
    @Test
    public void testSetLongText() {
        System.out.println("setLongText");
        Player player = new Player();
        Notification notification = new Notification("short message", "long message text", player);
        notification.setLongText("new longer text message");
        assertEquals("It should be 'new longer text message'", "new longer text message", notification.getLongText());
    }

    /**
     * Test of getPlayer method, of class Notification.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        Player player = new Player();
        player.setUserID("1");
        Notification notification = new Notification("short message", "long message text", player);
        assertEquals("Notification Player ID should be 1'", "1", notification.getPlayer().getUserID());
    }

    /**
     * Test of setPlayer method, of class Notification.
     */
    @Test
    public void testSetPlayer() {
        System.out.println("setPlayer");
        Player bob = new Player();
        bob.setUserID("1");
        Player bill = new Player();
        bill.setUserID("2");
        Notification notification = new Notification("short message", "long message text", bob);
        assertEquals("Notification Player ID should be 1'", "1", notification.getPlayer().getUserID());
        notification.setPlayer(bill);
        assertEquals("Notification Player ID should be 2'", "2", notification.getPlayer().getUserID());
    }

    /**
     * Test of getTimeSent method, of class Notification.
     */
    @Test
    public void testGetTimeSent() {
        System.out.println("getTimeSent");
        Player player = new Player();
        player.setUserID("1");
        Notification notification = new Notification("short message", "long message text", player);
        Date now = new Date();
        assertEquals("Time sent should be now'", now, notification.getTimeSent());
    }

    /**
     * Test of setTimeSent method, of class Notification.
     */
    @Test
    public void testSetTimeSent() {
        System.out.println("setTimeSent");
        Date date = new Date();
        Notification notification = new Notification(1, "short message", "long message", date);
        assertEquals("time sent should be then", date, notification.getTimeSent());
        date.setTime(656757645);
        notification.setTimeSent(date);
        if (notification.getTimeSent() != date) {
            fail();
        
        }  
        
    }
}
