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
		
		new Phrase("Vador", luc, "Luc, je suis ton père").save();
		
		
		Phrase phrase = Phrase.find("postBy.login", "luke").first();
		
		assertNotNull(phrase);
		assertEquals("Vador", phrase.author);
		
	}

}
