package data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James Upshall
 */
public class FightRequestTest {
    
    public FightRequestTest() {
    }

    /**
     * Test of getSenderID method, of class FightRequest.
     */
    @Test
    public void testGetSenderID() {
       System.out.println("getSenderID");
       FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
       assertEquals("the senderID should be 'senderID'","senderID", fightRequest.getSenderID());   
    }

    /**
     * Test of setSenderID method, of class FightRequest.
     */
    @Test
    public void testSetSenderID() {
        System.out.println("setSenderID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        fightRequest.setSenderID("new senderID");
        assertEquals("the senderID should be 'senderID'","new senderID", fightRequest.getSenderID());
    }

    /**
     * Test of getRecieverID method, of class FightRequest.
     */
    @Test
    public void testGetRecieverID() {
        System.out.println("getRecieverID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        assertEquals("the receiverID should be 'receiverID'","receiverID",fightRequest.getRecieverID());
        
    }

    /**
     * Test of setRecieverID method, of class FightRequest.
     */
    @Test
    public void testSetRecieverID() {
        System.out.println("setRecieverID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        fightRequest.setRecieverID("new receiverID");
        assertEquals("it should now be 'new receiverID'","new receiverID",fightRequest.getRecieverID());
        
    }

    /**
     * Test of getFightID method, of class FightRequest.
     */
    @Test
    public void testGetFightID() {
        System.out.println("getFightID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        assertEquals("it should be 'fightID'","fightID",fightRequest.getFightID());
    }

    /**
     * Test of setFightID method, of class FightRequest.
     */
    @Test
    public void testSetFightID() {
        System.out.println("setFightID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        fightRequest.setFightID("new fightID");
        assertEquals("it should be 'new fightID'","new fightID",fightRequest.getFightID());
    }

    /**
     * Test of getSenderMonsterID method, of class FightRequest.
     */
    @Test
    public void testGetSenderMonsterID() {
        System.out.println("getSenderMonsterID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        assertEquals("it should be 'senderMonsterID'","senderMonsterID",fightRequest.getSenderMonsterID());
        
    }

    /**
     * Test of setSenderMonsterID method, of class FightRequest.
     */
    @Test
    public void testSetSenderMonsterID() {
        System.out.println("setSenderMonsterID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        fightRequest.setSenderMonsterID("new monsterID");
        assertEquals("it should be 'new monsterID'","new monsterID", fightRequest.getSenderMonsterID());
    }

    /**
     * Test of getReceiverMonsterID method, of class FightRequest.
     */
    @Test
    public void testGetReceiverMonsterID() {
        System.out.println("getReceiverMonsterID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        assertEquals("should be 'receiverMonsterID'","receiverMonsterID",fightRequest.getReceiverMonsterID());
        
    }

    /**
     * Test of setReceiverMonsterID method, of class FightRequest.
     */
    @Test
    public void testSetReceiverMonsterID() {
        System.out.println("setReceiverMonsterID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        fightRequest.setReceiverMonsterID("new MonsterID");
        assertEquals("it should be 'new MonsterID'","new MonsterID",fightRequest.getReceiverMonsterID());
    }

    /**
     * Test of getSenderServerID method, of class FightRequest.
     */
    @Test
    public void testGetSenderServerID() {
        System.out.println("getSenderServerID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        assertEquals("it should be 1",1,fightRequest.getSenderServerID());
    }

    /**
     * Test of setSenderServerID method, of class FightRequest.
     */
    @Test
    public void testSetSenderServerID() {
        System.out.println("setSenderServerID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        fightRequest.setSenderServerID(8);
        assertEquals("it should be 8", 8, fightRequest.getSenderServerID());
        
    }

    /**
     * Test of getRecieverServerID method, of class FightRequest.
     */
    @Test
    public void testGetRecieverServerID() {
        System.out.println("getRecieverServerID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        assertEquals("it should be 2",2,fightRequest.getRecieverServerID());
        
    }

    /**
     * Test of setRecieverServerID method, of class FightRequest.
     */
    @Test
    public void testSetRecieverServerID() {
        System.out.println("setRecieverServerID");
        FightRequest fightRequest = new FightRequest("senderID",  "receiverID",  "fightID",  "senderMonsterID",  "receiverMonsterID", 1, 2);
        fightRequest.setRecieverServerID(5);
        assertEquals("it should be 5",5,fightRequest.getRecieverServerID());
        
    }

  
    
}
