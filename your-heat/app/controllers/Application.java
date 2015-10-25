package controllers;

import models.Project;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.project_form;
import views.html.commons.*;

public class Application extends Controller {

	public static final String PROJECT_NAME = "Your Heat";

	public Result index() {
		return ok(index.render(PROJECT_NAME));
	}

	public Result projects(Integer page) {
		return TODO;
	}

	private static final Form<Project> projectForm = Form.form(Project.class);

	public Result newProject() {
		return ok(project_form.render(projectForm));
	}

	public Result saveProject() {
		Form<Project> boundForm = projectForm.bindFromRequest();
		Project project = projectForm.get();
		project.save();
		return ok(String.format("Saved product %s", project));
	}

}
