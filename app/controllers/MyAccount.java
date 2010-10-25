package controllers;

import java.util.List;

import models.Phrase;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class MyAccount extends Controller {

	@Before
	static void setConnectedUser() {
		if (Security.isConnected()) {
			User user = User.find("byLogin", Security.connected()).first();
			renderArgs.put("user", user.login);
		}
	}

	public static void index() {
		User me = User.find("login", Security.connected()).first();
		render(me);

	}

	public static void form() {
		User me = User.find("login", Security.connected()).first();
		render(me);
	}

	public static void save(String password, String nickname) {
		User me = User.find("login", Security.connected()).first();
		me.password = password;
		me.nickname = nickname;

		// Validate
		validation.valid(me);
		if (validation.hasErrors()) {
			render("@form", me);
		}
		// Save
		me.save();
		index();
	}

}
