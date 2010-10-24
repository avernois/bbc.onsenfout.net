package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	@Before
	static void addDefaults() {
	    renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
	    renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
	}

	
    public static void index() {
        Phrase latestPhrase = Phrase.find("order by postedAt desc").first();
        List<Phrase> olderPhrases = Phrase.find("order by postedAt desc").from(1).fetch(10);
        
        System.out.println(latestPhrase.author);
        
        render(latestPhrase, olderPhrases);
    }


}