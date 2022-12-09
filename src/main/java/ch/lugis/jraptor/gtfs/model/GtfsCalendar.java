package ch.lugis.jraptor.gtfs.model;

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
	public void setThuesday(Boolean tuesday) {
		this.tuesday = tuesday;
	}
	public void setThuesday(int tuesday) {
		this.tuesday = getBooleanFromInt(tuesday);
	}
	public void setWednesday(Boolean wednesday) {
		this.wednesday = wednesday;
	}
	public void setWednesday(int wednesday) {
		this.wednesday = getBooleanFromInt(wednesday);
	}
	public void setThursday(Boolean thursday) {
		this.thursday = thursday;
	}
	public void setThursday(int thursday) {
		this.thursday = getBooleanFromInt(thursday);
	}
	public void setFriday(Boolean friday) {
		this.friday = friday;
	}
	public void setFriday(int friday) {
		this.friday = getBooleanFromInt(friday);
	}
	public void setSaturday(Boolean saturday) {
		this.saturday = saturday;
	}
	public void setSaturday(int saturday) {
		this.saturday = getBooleanFromInt(saturday);
	}
	public void setSunday(Boolean sunday) {
		this.sunday = sunday;
	}
	public void setSunday(int sunday) {
		this.sunday = getBooleanFromInt(sunday);
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
	
	// Private methods
	private Boolean getBooleanFromInt(int intValue) {
		if (intValue >= 1) {
			return true;
		} else {
			return false;
		}
	}
}
