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
	
	@Id
	public long id;
	
	@Required
	public String name;
	
	public String supportName; 
	public LinkedList<Meter> meters;
	
	private static List<Project> projects = new ArrayList<Project>();
	
	public static List<Project> findAll(){
		return new ArrayList<Project>(projects);
	}
	
	public void save(){
		projects.add(this);
	}
}
