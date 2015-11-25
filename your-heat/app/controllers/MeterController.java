package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;

import constants.Constants;
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
			String fileName = meterData.getFilename().toString();
			LinkedList<MeterData> meterDataList = readFile(file);
			return redirect(routes.Application.newProject());
		}
		
		return TODO;
	}

	private LinkedList<MeterData> readFile(File file) {
		System.out.println(file.getName());
		LinkedList<MeterData> meterDataList = new LinkedList<MeterData>();
		String line = null;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			br.readLine();
			br.readLine();
			br.readLine();
			br.readLine();
			br.readLine();
			br.readLine();
			br.readLine();
			
			while ((line = br.readLine()) != null) {
				//set up the date
				String[] dataTokens = line.split(Constants.TAB_DELIMITER);
				System.out.println(Arrays.toString(dataTokens));
				String[] dateTokens = dataTokens[1].split(Constants.DATE_DELIMITER);
				short m = Short.parseShort(dateTokens[0]);
				short d = Short.parseShort(dateTokens[1]);
				short y = Short.parseShort(dateTokens[2]);
				
				// ///////////set up the time///////////////
				String[] timeTokens1 = dataTokens[2].split(Constants.TIME_DELIMITER);
				short h1 = Short.parseShort(timeTokens1[0]);
				short min1 = Short.parseShort(timeTokens1[1]);
				
				float con = Float.parseFloat(dataTokens[4]);
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				Date date = new Date(y - 1900, m - 1, d, h1, min1);
				cal.setTime(date);

				MeterData meterData = new MeterData(cal.getTimeInMillis());

				if (con < 0) {
					meterData.setGenkWh(con * (-1));
					meterData.setGenkW(con * (-1) * 4);
				} else {
					meterData.setKWh(con);
					meterData.setkW(con * 4);
				}

				meterData.setDate(cal.getTime());
				meterData.setDateValue(cal.getTime().getTime());

				meterDataList.add(meterData);
				
				
			}
			
		} catch (FileNotFoundException e){
			
		}catch (IOException e){
			
		}
		Collections.sort(meterDataList);
		
		return meterDataList;
	}
}
