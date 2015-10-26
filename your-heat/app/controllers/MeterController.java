package controllers;

import java.io.File;

import models.MeterData;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

public class MeterController extends Controller{

	private static final Form<MeterData> meterDataForm = Form.form(MeterData.class);
	
	public Result loadMeterData(){
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart meterData = body.getFile("meterData");
		if(meterData != null){
			File file = meterData.getFile();
			String fileName = meterData.getFilename();
			return ok("file uploaded");
		}
		
		return TODO;
	}
}
