package data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Hopkins (dah27)
 */
public class FriendTest {
    
    public FriendTest() {
    }
    /**
     * Test of isFriendshipConfirmed method, of class Friend.
     */
    @Test
    public void testIsFriendshipConfirmed_0args() {
        System.out.println("isFriendshipConfirmed");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "N");
        assertFalse(friend.isFriendshipConfirmed());  
    }

    /**
     * Test of setFriendshipConfirmed method, of class Friend.
     */
    @Test
    public void testSetFriendshipConfirmed() {
        System.out.println("setFriendshipConfirmed");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "N");
        assertFalse(friend.isFriendshipConfirmed()); 
        friend.setFriendConfirmed("Y");
        assertTrue(friend.isFriendshipConfirmed());
    }

    /**
     * Test of setFriendConfirmed method, of class Friend.
     */
    @Test
    public void testSetFriendConfirmed() {
        System.out.println("setFriendConfirmed");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "N");
        friend.setFriendConfirmed("Y");
        assertTrue(friend.isFriendshipConfirmed());
        friend.setFriendConfirmed("N");
        assertFalse(friend.isFriendshipConfirmed());
    }

    /**
     * Test of isFriendshipConfirmed method, of class Friend.
     */
    @Test
    public void testIsFriendshipConfirmed_Friend() {
        System.out.println("isFriendshipConfirmed");
        Player player1 = new Player("dan", "dah27@aber.ac.uk", "password", 100, "Rodger");
        Player player2 = new Player("james", "jau11@aber.ac.uk", "password", 100, "LichChild");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("friendship should be true", true, friend.isFriendshipConfirmed(friend));
        
    }

    /**
     * Test of getFriendshipID method, of class Friend.
     */
    @Test
    public void testGetFriendshipID() {
        System.out.println("getFriendshipID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("FriendshipID should be Bob", "Bob", friend.getFriendshipID());
    }

    /**
     * Test of setFriendshipID method, of class Friend.
     */
    @Test
    public void testSetFriendshipID() {
        System.out.println("setFriendshipID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("FriendshipID should be Bob", "Bob", friend.getFriendshipID());
        friend.setFriendshipID("newID");
        assertEquals("FriendshipID should be newID", "newID", friend.getFriendshipID());
    }

    /**
     * Test of getRemoteUserID method, of class Friend.
     */
    @Test
    public void testGetRemoteUserID() {
        System.out.println("getRemoteUserID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("RemoteUserID should be dah27@aber.ac.uk", "dah27@aber.ac.uk", friend.getRemoteUserID());
    }

    /**
     * Test of setRemoteUserID method, of class Friend.
     */
    @Test
    public void testSetRemoteUserID() {
        System.out.println("setRemoteUserID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("RemoteUserID should be dah27@aber.ac.uk", "dah27@aber.ac.uk", friend.getRemoteUserID());
        friend.setRemoteUserID("newID");
        assertEquals("RemoteUserID should be newID", "newID", friend.getRemoteUserID());
    }

    /**
     * Test of getLocalUserID method, of class Friend.
     */
    @Test
    public void testGetLocalUserID() {
        System.out.println("getLocalUserID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("RemoteUserID should be jau11@aber.ac.uk", "jau11@aber.ac.uk", friend.getLocalUserID());
    }

    /**
     * Test of setLocalUserID method, of class Friend.
     */
    @Test
    public void testSetLocalUserID() {
        System.out.println("setLocalUserID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("RemoteUserID should be jau11@aber.ac.uk", "jau11@aber.ac.uk", friend.getLocalUserID());
        friend.setLocalUserID("newID");
        assertEquals("LocalUserID should be newID", "newID", friend.getLocalUserID());
    }

    /**
     * Test of getLocalServerID method, of class Friend.
     */
    @Test
    public void testGetLocalServerID() {
        System.out.println("getLocalServerID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("LocalServerID should be 12", 12, friend.getLocalServerID());
    }

    /**
     * Test of setLocalServerID method, of class Friend.
     */
    @Test
    public void testSetLocalServerID() {
        System.out.println("setLocalServerID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 12, "Y");
        assertEquals("LocalServerID should be 12", 12, friend.getLocalServerID());
        friend.setLocalUserID("newID");
        assertEquals("LocalServerID should be newID", 12, friend.getLocalServerID());
    }

    /**
     * Test of getRemoteServerID method, of class Friend.
     */
    @Test
    public void testGetRemoteServerID() {
        System.out.println("getRemoteServerID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 13, "Y");
        assertEquals("LocalServerID should be 12", 13, friend.getRemoteServerID());
    }

    /**
     * Test of setRemoteServerID method, of class Friend.
     */
    @Test
    public void testSetRemoteServerID() {
        System.out.println("setRemoteServerID");
        Friend friend = new Friend ("Bob", "dah27@aber.ac.uk", "jau11@aber.ac.uk", 12, 13, "Y");
        assertEquals("LocalServerID should be 12", 13, friend.getRemoteServerID());
        friend.setLocalUserID("newID");
        assertEquals("LocalServerID should be newID", 13, friend.getRemoteServerID());
    }
}
