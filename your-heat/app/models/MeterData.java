package models;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.avaje.ebean.Model;

import constants.Constants;
import play.data.validation.Constraints.Required;


/**
 * Model for the interval data which is directly gathered from a meter
 * @author Jose Camilo Uzquiano
 *
 */
public class MeterData extends Model implements Comparable<MeterData> {
	
	private float kWh, cost, kW, genkW, genkWh, kVarh, kVar;
	private short julianDay;
	private Date date;
	private long dateValue;
	private String meterNumber;
	private String dayType;
	
	@Required
	public File file;
	
	
	public MeterData(long dateValue){
		this.dateValue = dateValue;
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(dateValue);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			dayType = "Weekend";
		} else {
			dayType = "WorkDay";
		}
		
		date = new Date(dateValue);
	}
	
	private void setJulianDay(int month, int day) {
		int days = 0;
		for (int m = 0; m < month - 1; m++) {
			days = days + Constants.MONTH_LENGTHS[m];
		}
		this.julianDay = (short) (days + day);

	}

	public float getKWh() {
		return kWh;
	}

	public void setKWh(float kWh) {
		this.kWh = kWh;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getkW() {
		return kW;
	}

	public short getJulianDay() {
		return julianDay;
	}


	@Override
	public String toString() {
		return "IntervalData [kW=" + kW + ", dateValue=" + dateValue + "]";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getDateValue() {
		return dateValue;
	}

	public void setDateValue(long dateValue) {
		this.dateValue = dateValue;
	}

	public void setJulianDay(short julianDay) {
		this.julianDay = julianDay;
	}

	public float getkVarh() {
		return kVarh;
	}

	public void setkVarh(float kVarh) {
		this.kVarh = kVarh;
	}

	public float getkVar() {
		return kVar;
	}

	public void setkVar(float kVar) {
		this.kVar = kVar;
	}

	public void setkW(float kW) {
		this.kW = kW;
	}

	@Override
	public int compareTo(MeterData data) {
		long comparedTime = data.getDateValue();
		if (this.dateValue > comparedTime) {
			return 1;
		} else if (this.dateValue == comparedTime) {
			return 0;
		} else {
			return -1;
		}
	}

	public float getGenkW() {
		return genkW;
	}

	public void setGenkW(float genkW) {
		this.genkW = genkW;
	}

	public float getGenkWh() {
		return genkWh;
	}

	public void setGenkWh(float genkWh) {
		this.genkWh = genkWh;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getDaytype() {
		return dayType;
	}

	public void setDaytype(String daytype) {
		this.dayType = daytype;
	}
	
}
