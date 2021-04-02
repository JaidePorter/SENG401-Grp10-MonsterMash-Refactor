/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import java.util.Date;
//import data.Player;

/**
 *
 * @author Llionv
 */
public class PlayerTest {

    public PlayerTest() {
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
     * Test of getId method, of class Player.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("Id should be James", "James", player.getUserID());

    }

    /**
     * Test of setId method, of class Player.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Player player = new Player("James","jau1@aber.ac.uk", "password1", 100, "Bill");
        player.setUserID("69");
        assertEquals("Id should be 69", "69", player.getUserID());

    }

    /**
     * Test of getPassword method, of class Player.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Player player = new Player("James","jau1@aber.ac.uk", "password1", 100, "Bill");
        assertEquals("password should be password1", "password1", player.getPassword());

    }

    /**
     * Test of setPassword method, of class Player.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        player.setPassword("mittens");
        assertEquals("password should be mittens", "mittens", player.getPassword());


    }

    /**
     * Test of getEmail method, of class Player.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("the email should be jau1@aber.ac.uk", "jau1@aber.ac.uk", player.getUsername());

    }

    /**
     * Test of setEmail method, of class Player.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        player.setUsername("lwv@aber.ac.uk");
        assertEquals("the email should be lwv@aber.ac.uk", "lwv@aber.ac.uk", player.getUsername());

    }

    /**
     * Test of getMoney method, of class Player.
     */
    @Test
    public void testGetMoney() {
        System.out.println("getMoney");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        assertEquals("the money should be 100", 100, player.getMoney());

    }

    /**
     * Test of setMoney method, of class Player.
     */
    @Test
    public void testSetMoney() {
        System.out.println("setMoney");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        player.setMoney(200);
        assertEquals("money should be 200", 200, player.getMoney());

    }

    /**
     * Test of getFriends method, of class Player.
     */
    @Test
    public void testGetFriendY() {
        System.out.println("getFriends");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        Player friend = new Player("Llion","lwv@aber.ac.uk", 12);
        player.addFriend(friend);
        assertEquals("friend ID should be Llion", "Llion", player.getFriend(0).getUserID());
    }

    /**
     * Test of getFriends method, of class Player.
     */
    @Test
    public void testGetFriendN() {
        System.out.println("getFriends");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
        Player playerv2 = new Player("Dan","dah27@aber.ac.uk", "password", 100,"Rodger");
        Friend friend = new Friend("jau1@aber.ac.uk","dah27@aber.ac.uk","11",23,34,"N");
      
        player.addFriend(playerv2);
       
        assertEquals("friend should be plaerv2",
                playerv2, player.getFriend(0));
        
    }

    /**
     * Test of getNotifications method, of class Player.
     */
    @Test
    public void testGetNotifications() {
        System.out.println("getNotifications");
        Player player = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
     
        Notification noti = new Notification("short text", "long text",player);
        ArrayList notices = new ArrayList();
        notices.add(noti);
        player.setNotifications(notices);
        assertEquals("notification should be noti",noti,player.getNotifications().get(0));
        
        

    }

    /**
     * Test of setNotifications method, of class Player.
     */
    @Test
    public void testSetNotifications() {
        System.out.println("setNotifications");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Notification notice = new Notification("short", "long", player);
        ArrayList notices = new ArrayList();
        notices.add(notice);
        player.setNotifications(notices);
        assertEquals("notification should be notice",notice, player.getNotifications().get(0));
    }

    /**
     * Test of getMonsters method, of class Player.
     */
    @Test
    public void testGetMonsters() {
        System.out.println("getMonsters");
        Player player = new Player("Dan","dah27@aber.ac.uk", "password", 100, "Rodger");
       Monster monster = new Monster("Name", "dah27@aber.ac.uk" );
        ArrayList monsterarr = new ArrayList();
        monsterarr.add(monster);
        player.setMonsters(monsterarr);
        assertEquals("Monster should be monster",monster,player.getMonsters().get(0));
    }

    /**
     * Test of setMonsters method, of class Player.
     */
    @Test
    public void testSetMonsters() {
        System.out.println("setMonsters");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Monster monster = new Monster("Name", "dah27@aber.ac.uk");
        ArrayList monsterarr = new ArrayList();
        monsterarr.add(monster);
        player.setMonsters(monsterarr);
        assertEquals("Monster should be monster",monster, player.getMonsters().get(0));
    }

    /**
     * Test of addFriend method, of class Player.
     */
    @Test
    public void testAddFriend() {
        System.out.println("addFriend");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Player playerv2 = new Player("James","jau1@aber.ac.uk", "password", 100, "Bill");
     
      player.addFriend(playerv2);  
        assertEquals("Friend should be friend",playerv2, player.getFriend(0));
        
        // TODO review the generated test code and remove the default call to fail.
    }

 

    /**
     * Test of addMonster method, of class Player.
     */
    @Test
    public void testAddMonster() {
        System.out.println("addMonster");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Monster monster = new Monster("Name","jau1@aber.ac.uk");
        //ArrayList monsterarr = new ArrayList();
        player.addMonster(monster);
        assertEquals("Monster should be added",monster, player.getMonsters().get(1));
    }

    /**
     * Test of removeMonster method, of class Player.
     */
    @Test
    public void testRemoveMonster() {
        System.out.println("removeMonster");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Monster monster = new Monster("Name","dah27@aber.ac.uk");
        //ArrayList monsterarr = new ArrayList();
        player.addMonster(monster);
        assertEquals("Monster should be added",monster, player.getMonsters().get(1));
        player.removeMonster(monster);
   
        assertEquals("size should be 1",1,player.getMonsters().size());
    }

    /**
     * Test of addNotification method, of class Player.
     */
    @Test
    public void testAddNotification() {
        System.out.println("addNotification");
        Player player = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Notification notice = new Notification("short","long", player);
        //ArrayList notices = new ArrayList();
        player.addNotification(notice);
        assertEquals("Notification should be added",notice, player.getNotifications().get(2));
    }
    
    @Test
    public void testsortByMoney() {
        System.out.println("sortByMoney");
        Player player1 = new Player ("Dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Player player2 = new Player ("James","jau1@aber.ac.uk", "password", 120, "Bill");
        Player player3 = new Player ("Llion","lwv@aber.ac.uk", "password", 50, "Bobby");
        Player player4 = new Player ("sam","agsda@aber.ac.uk", "password", 105, "John");
        
        ArrayList<Player> array = new ArrayList();
        array.add(player1);
        array.add(player2);
        array.add(player3);
        array.add(player4);
        
       
        assertEquals("player2 should be at index 0, most money", player2, player1.sortByMoney(array).get(0));
        assertEquals("player1 should be at index 1, second most money", player4, player1.sortByMoney(array).get(1));
        assertEquals("player4 should be at index 3, third most money", player1, player1.sortByMoney(array).get(2));
        assertEquals("player3 should be at index 2, fourth most money", player3, player1.sortByMoney(array).get(3));
    }
}
