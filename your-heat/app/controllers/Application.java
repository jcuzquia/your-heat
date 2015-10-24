package controllers;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.user.AuthUser;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.User;
import play.Routes;
import play.mvc.Controller;
import play.mvc.Http.Session;
import play.mvc.Result;
import views.html.index;
import views.html.profile;
import controllers.Signup;


public class Application extends Controller {

	public static final String USER_ROLE = "user";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String FLASH_MESSAGE_KEY = "message";
	
    public Result index() {
        return ok(index.render());
    }
    
    
    public static Result jsRoutes() {
		
    return ok(Routes.javascriptRouter("jsRoutes", controllers.routes.javascript));	
//    	return ok(
//				Routes.javascriptRouter("jsRoutes",
//						controllers.routes.javascript.Signup.forgotPassword()))
//				.as("text/javascript");
	}
    
    @Restrict(@Group(Application.USER_ROLE))
    public static Result restricted(){
    	final User localUser = getLocalUser(session());
    	return ok(profile.render(localUser));
    }


	public static User getLocalUser(Session session) {
		
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		return null;
	}

}
