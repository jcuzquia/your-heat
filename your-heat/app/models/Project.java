package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Project extends Model{
	
//	@Id
//	public long id;
	
	@Required
	public String name;
	
	public String supportName; 
	public LinkedList<Meter> meters;
	
	private static Model.Finder<String, Project> find = new Model.Finder<String, Project>(Project.class);
	
	public static List<Project> findAll(){
		return Project.find.orderBy("name").findList();
	}
	
	public static Project retrieve(String name){
		return find.ref(name);
	}
}
