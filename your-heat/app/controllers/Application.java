package controllers;

import java.util.List;

import models.Meter;
import models.Project;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.project_form;
import views.html.projects_list;
import views.html.project_info;
import views.html.commons.*;

public class Application extends Controller {

	public static final String PROJECT_NAME = "Your Heat";
	
	private static Logger.ALogger logger = Logger.of(Application.class);

	public Result index() {
		return ok(index.render(PROJECT_NAME));
	}

	public Result projects(Integer page) {
		List<Project> projects = Project.findAll();
		
		return ok(projects_list.render(projects));
	}

	private static final Form<Project> projectForm = Form.form(Project.class);
	private static final Form<Meter> meterForm = Form.form(Meter.class);

	public Result newProject() {
		return ok(project_form.render(projectForm));
	}

	public Result saveProject() {
		Form<Project> boundForm = Form.form(Project.class).bindFromRequest();
		if (boundForm.hasErrors()){
			logger.debug("Error");
			return badRequest(project_form.render(projectForm));
		}
		Project project = boundForm.get();
		if(project != null){
			System.out.println(project.name);
			project.save();
			return redirect(routes.Application.projects(1));
		}
		
		return redirect(routes.Application.projects(1));
	}
	
	public Result projectInfo(String name){
		Project project = Project.retrieve(name);
		return ok(project_info.render(project, meterForm));
	}
	
	public Result editProject(String name){
		Project project = Project.retrieve(name);
		Form<Project> ourForm = Form.form(Project.class).fill(project);
		return ok(project_form.render(ourForm));
	}
	
	public Result delete(String name){
		Project project = Project.retrieve(name);
		if (project == null){
			return notFound(name + " is not on file.");
		}
		project.delete();
		return redirect(routes.Application.projects(1));
		
	}

}
