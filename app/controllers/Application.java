package controllers;

import controllers.composition.Logging.Logs;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	@Logs(action = "user_log")
    public static Result index() {
		System.out.println("Controller method invoked");
		flash("message", "Hello");
        return ok(index.render("Your new application is ready."));
    }

}
