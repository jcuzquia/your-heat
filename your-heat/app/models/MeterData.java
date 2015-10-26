package models;

import java.io.File;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

public class MeterData extends Model {
	
	@Required
	public File file;
}
