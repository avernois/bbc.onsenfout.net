import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
 
    @Test
    public void testThatRouteToPhraseFrom() {
        Response response = GET("/phrases/Antoine");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    
    @Test
    public void testAdminSecurity() {
        Response response = GET("/admin");
        assertStatus(302, response);
        assertHeaderEquals("Location", "http://localhost/login", response);
    }
    
    @Test
    public void testAtom() {
    	Response response = GET("/atom");
    	assertIsOk(response);
    	assertContentType("text/xml", response);
    	assertCharset("utf-8", response);
    }

    
}