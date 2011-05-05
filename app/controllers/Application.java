package controllers;

import java.util.List;

import models.Phrase;
import play.Play;
import play.modules.paginate.ModelPaginator;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;

public class Application extends Controller {

	@Before
	static void addDefaults() {
		renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
		renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
		renderArgs.put("blogUrl", Play.configuration.getProperty("blog.url"));
	}

	public static void index() {
		Phrase latestPhrase = Phrase.find("order by postedAt desc").first();
		List<Phrase> olderPhrases = Phrase.find("order by postedAt desc").from(1).fetch();

		ValuePaginator paginator = new ValuePaginator(olderPhrases);
		paginator.setPageSize(10);

		render(latestPhrase, paginator);
	}

	public static void atom() {
		request.format = "xml";
		List<Phrase> lastPhrases = Phrase.find("order by postedAt desc").fetch(15);
		render(lastPhrases);
	}

	public static void phrasesfrom(String from) {
		List<Phrase> saidBy = Phrase.findByAuthor(from);
		render(saidBy, from);
	}

	public static void phrase(long id) {
		Phrase phrase = Phrase.findById(id);
		render(phrase);
	}
}