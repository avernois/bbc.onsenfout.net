package controllers;

import java.util.List;

import models.Phrase;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

public class Application extends Controller {

	@Before
	static void addDefaults() {
	    renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
	    renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
	}

    public static void index() {
        Phrase latestPhrase = Phrase.find("order by postedAt desc").first();
        List<Phrase> olderPhrases = Phrase.find("order by postedAt desc").from(1).fetch(10);
        render(latestPhrase, olderPhrases);
    }
}