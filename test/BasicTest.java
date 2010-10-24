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
	public void createAndRetrievePhrase() {
		new Phrase("Vador", "Luc", "Luc, je suis ton père").save();
		
		Phrase phrase = Phrase.find("byAuthor", "Vador").first();
		
		assertNotNull(phrase);
		assertEquals("Luc", phrase.postBy);
		
	}

}
