import models.Author;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class AuthorTest extends UnitTest {
	@Before
    public void setup() {
        Fixtures.deleteAll();
    }
	
	@Test
	public void testCompareSameAuthor() {
		Author author = Author.findOrCreate("Luke");
		
		assertEquals(0, author.compareTo(author));		
	}
	
	@Test
	public void testCompareDifferentAuthorSup() {
		Author author1 = Author.findOrCreate("Luke");
		Author author2 = Author.findOrCreate("Vador");
		
		assertTrue(author1.compareTo(author2) < 0);		
	}
	
	@Test
	public void testCompareDifferentAuthorInf() {
		Author author2 = Author.findOrCreate("Luke");
		Author author1 = Author.findOrCreate("Vador");
		
		assertTrue(author1.compareTo(author2) > 0);		
	}
	
	@Test 
	public void testIncScore() {
		Author author = Author.findOrCreate("Luke");
		author.incScore();
		
		assertEquals(Integer.valueOf(1), author.score);
	}
	
	@Test
	public void testFindOrCreateNewAuthor() {
		Author author = Author.findOrCreate("Luke");
		assertEquals("Luke", author.name);
		assertEquals(Integer.valueOf(0), author.score);
	}
	
	@Test
	public void testFindOrCreateExistingAuthor() {
		Author author = Author.findOrCreate("Luke");
		
		Author other = Author.findOrCreate("Luke");

		assertEquals(author.id, other.id);
		assertEquals(author.name, other.name);
		assertEquals(author.score, other.score);
	}
	
	@Test
	public void testToString() {
		Author author = Author.findOrCreate("Luke");
		assertEquals("Luke", author.toString());
	}
}

