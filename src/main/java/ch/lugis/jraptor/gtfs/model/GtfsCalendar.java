package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

public class GtfsCalendar {
	// Fields
	private String serviceId;
	private Boolean monday;
	private Boolean tuesday;
	private Boolean wednesday;
	private Boolean thursday;
	private Boolean friday;
	private Boolean saturday;
	private Boolean sunday;
	private GtfsDate startDate;
	private GtfsDate endDate;
	public static Map<String, String> mapSetters = new HashMap<>();
	
	static {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("service_id", "setServiceId");
		tempFields.put("monday", "setMonday");
		tempFields.put("tuesday", "setTuesday");
		tempFields.put("wednesday", "setWednesday");
		tempFields.put("thursday", "setThursday");
		tempFields.put("friday", "setFriday");
		tempFields.put("saturday", "setSaturday");
		tempFields.put("sunday", "setSunday");
		tempFields.put("start_date", "setStartDate");
		tempFields.put("end_date", "setEndDate");
		mapSetters = Collections.unmodifiableMap(tempFields);
	}
	
	
	// Constructor
	public GtfsCalendar() {}
	
	public GtfsCalendar(String serviceId, Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday,
			Boolean friday, Boolean saturday, Boolean sunday, GtfsDate startDate, GtfsDate endDate) {
		this.serviceId = serviceId;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public GtfsCalendar(String serviceId, int monday, int tuesday, int wednesday, int thursday,
			int friday, int saturday, int sunday, String startDate, String endDate) {
		this.serviceId = serviceId;
		this.monday = getBooleanFromInt(monday);
		this.tuesday = getBooleanFromInt(tuesday);
		this.wednesday = getBooleanFromInt(wednesday);
		this.thursday = getBooleanFromInt(thursday);
		this.friday = getBooleanFromInt(friday);
		this.saturday = getBooleanFromInt(saturday);
		this.sunday = getBooleanFromInt(sunday);
		setStartDate(startDate);
		setEndDate(endDate);
	}
	// Only Strings
	public GtfsCalendar(String serviceId, String monday, String tuesday, String wednesday, String thursday,
			String friday, String saturday, String sunday, String startDate, String endDate) {
		this.serviceId = serviceId;
		this.monday = getBooleanFromInt(Integer.valueOf(monday));
		this.tuesday = getBooleanFromInt(Integer.valueOf(tuesday));
		this.wednesday = getBooleanFromInt(Integer.valueOf(wednesday));
		this.thursday = getBooleanFromInt(Integer.valueOf(thursday));
		this.friday = getBooleanFromInt(Integer.valueOf(friday));
		this.saturday = getBooleanFromInt(Integer.valueOf(saturday));
		this.sunday = getBooleanFromInt(Integer.valueOf(sunday));
		setStartDate(startDate);
		setEndDate(endDate);
	}
	
	// Getters
	public String getServiceId() {
		return serviceId;
	}
	public Boolean getMonday() {
		return monday;
	}
	public int getMondayAsInt() {
		return monday  ? 1 : 0;
	}
	public Boolean getTuesday() {
		return tuesday;
	}
	public int getTuesdayAsInt() {
		return tuesday  ? 1 : 0;
	}
	public Boolean getWednesday() {
		return wednesday;
	}
	public int getWednesdayAsInt() {
		return wednesday  ? 1 : 0;
	}
	public Boolean getThursday() {
		return thursday;
	}
	public int getThursdayAsInt() {
		return thursday  ? 1 : 0;
	}
	public Boolean getFriday() {
		return friday;
	}
	public int getFridayAsInt() {
		return friday  ? 1 : 0;
	}
	public Boolean getSaturday() {
		return saturday;
	}
	public int getSaturdayAsInt() {
		return saturday  ? 1 : 0;
	}
	public Boolean getSunday() {
		return sunday;
	}
	public int getSundayAsInt() {
		return sunday  ? 1 : 0;
	}
	public GtfsDate getStartDate() {
		return startDate;
	}
	public String getStartDateAsString() {
		return startDate.getDateAsString();
	}
	public GtfsDate getEndDate() {
		return endDate;
	}
	public String getEndDateAsString() {
		return endDate.getDateAsString();
	}
	
	// Setters
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public void setMonday(Boolean monday) {
		this.monday = monday;
	}
	public void setMonday(int monday) {
		this.monday = getBooleanFromInt(monday);
	}
	public void setMonday(String monday) {
		this.monday = getBooleanFromString(monday);
	}
	public void setTuesday(Boolean tuesday) {
		this.tuesday = tuesday;
	}
	public void setTuesday(int tuesday) {
		this.tuesday = getBooleanFromInt(tuesday);
	}
	public void setTuesday(String tuesday) {
		this.tuesday = getBooleanFromString(tuesday);
	}
	public void setWednesday(Boolean wednesday) {
		this.wednesday = wednesday;
	}
	public void setWednesday(int wednesday) {
		this.wednesday = getBooleanFromInt(wednesday);
	}
	public void setWednesday(String wednesday) {
		this.wednesday = getBooleanFromString(wednesday);
	}
	public void setThursday(Boolean thursday) {
		this.thursday = thursday;
	}
	public void setThursday(int thursday) {
		this.thursday = getBooleanFromInt(thursday);
	}
	public void setThursday(String thursday) {
		this.thursday = getBooleanFromString(thursday);
	}
	public void setFriday(Boolean friday) {
		this.friday = friday;
	}
	public void setFriday(int friday) {
		this.friday = getBooleanFromInt(friday);
	}
	public void setFriday(String friday) {
		this.friday = getBooleanFromString(friday);
	}
	public void setSaturday(Boolean saturday) {
		this.saturday = saturday;
	}
	public void setSaturday(int saturday) {
		this.saturday = getBooleanFromInt(saturday);
	}
	public void setSaturday(String saturday) {
		this.saturday = getBooleanFromString(saturday);
	}
	public void setSunday(Boolean sunday) {
		this.sunday = sunday;
	}
	public void setSunday(int sunday) {
		this.sunday = getBooleanFromInt(sunday);
	}
	public void setSunday(String sunday) {
		this.sunday = getBooleanFromString(sunday);
	}
	public void setStartDate(GtfsDate startDate) {
		this.startDate = startDate;
	}
	public void setStartDate(String datestring) {
		this.startDate = new GtfsDate(datestring);
	}
	public void setEndDate(GtfsDate endDate) {
		this.endDate = endDate;
	}
	public void setEndDate(String datestring) {
		this.endDate = new GtfsDate(datestring);
	}
	
	@Override
	public String toString() {
		return "GtfsCalendar [serviceId=" + serviceId + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday="
				+ wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday=" + saturday + ", sunday="
				+ sunday + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	// Public static methods
	public static Method[] getOrderedMethodArray(String[] gtfsHeader) {
		Class<GtfsCalendar> classObject = GtfsCalendar.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}	
	
	// Private methods
	private Boolean getBooleanFromInt(int intValue) {
		if (intValue >= 1) {
			return true;
		} else {
			return false;
		}
	}
	private Boolean getBooleanFromString(String stringValue) {
		if (stringValue == null || stringValue.equals("0") || stringValue.isBlank()) {
			return false;
		} else {
			return true;
		}
	}
}
