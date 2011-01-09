import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

	@Test
    public void createAndRetrieveUser() {
    	new User("luke", "secret", "Luke S.").save();
    	
    	User luc = User.find("byLogin", "luke").first();
    	assertNotNull(luc);
    	assertEquals("Luke S.", luc.nickname);
    }
    
    @Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User("bob", "secret", "Bob").save();
        
        // Test 
        assertNotNull(User.connect("bob", "secret"));
        assertNull(User.connect("bob", "badpassword"));
        assertNull(User.connect("plop", "secret"));
    }    
    
	@Test
	public void createAndRetrievePhrase() {
		User luc = new User("luke", "secret", "Luke S.").save();
		
		new Phrase(luc, "Luke, je suis ton père").save();
		
		
		Phrase phrase = Phrase.find("postBy.login", "luke").first();
		
		assertNotNull(phrase);
		assertEquals("Luke, je suis ton père", phrase.phrase);
		assertEquals("luke", phrase.postBy.login);
		
	}
	
	
	@Test
	public void testAddAuthors() {
	    // Create a new user and save it
	    User poster = new User("toto@gmail.com", "secret", "Toto").save();
	    
	    // Create a new post
	    Phrase aPhrase = new Phrase(poster, "Hello world").save();
	    
	    // Well
	    assertEquals(0, Phrase.findByAuthor("Luke").size());
	    
	    // Tag it now
	    aPhrase.addAuthor("Luke").save();
	    
	    // Check
	    assertEquals(1, Phrase.findByAuthor("Luke").size());        
	    assertEquals(0, Phrase.findByAuthor("Vador").size());
	    
	}


	@Test
	public void testAuthorScoreIncWhenAddedToAPhrase() {
	    // Create a new user and save it
	    User poster = new User("toto@gmail.com", "secret", "Toto").save();
	 
	    
	    Author luke = Author.findOrCreate("Luke");
	    Integer oldScore = luke.score;
	    
	    // Create a new post
	    Phrase aPhrase = new Phrase(poster, "Hello world").save();
	    
	    // Attribute it to Luke
	    aPhrase.addAuthor("Luke").save();
	    
	    luke = Author.findOrCreate("Luke");
	    
	    // Check that luke score has increased
	    assertEquals(Integer.valueOf(oldScore + 1), luke.score);
	    
	    aPhrase = new Phrase(poster, "Hello world").save();
	    aPhrase.addAuthor("Luke").save();
	    
	    // Check that luke score has increased	    
	    assertEquals(Integer.valueOf(oldScore + 2), luke.score);
	}

	
}
