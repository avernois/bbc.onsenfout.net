package controllers;

import java.util.List;

import models.Author;
import models.Phrase;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;

public class Votes extends Controller {

	@Before
	static void addDefaults() {
	    renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
	    renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
	    renderArgs.put("blogUrl", Play.configuration.getProperty("blog.url"));
	}

	
	public static void index() {
		List<Phrase> bestPhrases = Phrase.find("order by score desc").fetch(15);
		render(bestPhrases);
	}

	public static void voteFor(Long id) {
		Phrase phrase = Phrase.findById(id);
		phrase.incScore();
		phrase.save();

		for (Author author : phrase.authors) {
			author.incScore();
			author.save();
		}

		index();
	}
}
