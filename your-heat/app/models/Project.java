package models;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Project extends Model{
	
	@Id
	public long id;
	
	@Required
	public String projectName;
	
	public String supportName; 
	public LinkedList<Meter> meters;
	
	
}
