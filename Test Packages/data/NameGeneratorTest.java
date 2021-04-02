package data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import data.NameGenerator;

/**
 *
 * @author Daniel Hopkins (dah27)
 */
public class NameGeneratorTest {
    
    public NameGeneratorTest() {
    }
    
    /**
     * Test of getName method, of class NameGenerator.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        //Player player = new Player("James","jau1@aber.ac.uk", "password1", 100, "Bill");
        //Monster monster = new Monster("Name", "1");
        NameGenerator breed = new NameGenerator();
        //breed.getName();
        assertNotNull("this should create a random name", NameGenerator.getName());
        
        String s = new String();
        s = NameGenerator.getName();
        assertTrue(s.length()>3 && s.length()<50);
    }
}
