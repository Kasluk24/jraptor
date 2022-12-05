package ch.lugis.jraptor.gtfs.model;

public class GtfsCalendarDates {
	// Fields
	private String serviceID;
	private GtfsDate date;
	private GtfsCalendarExceptionType exceptionType;
	
	// Constructor
	public GtfsCalendarDates() {}

	public GtfsCalendarDates(String serviceID, GtfsDate date, GtfsCalendarExceptionType exceptionType) {
		this.serviceID = serviceID;
		this.date = date;
		this.exceptionType = exceptionType;
	}
	public GtfsCalendarDates(String serviceID, String date, int exceptionType) {
		this.serviceID = serviceID;
		this.date.setDate(date);
		if (GtfsCalendarExceptionType.getTypeByCode(exceptionType) != null) {
			this.exceptionType = GtfsCalendarExceptionType.getTypeByCode(exceptionType);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS calendar exception type", 
							exceptionType));
		}
	}

	// Getters
	public String getServiceID() {
		return serviceID;
	}
	public GtfsDate getDate() {
		return date;
	}
	public String getDateAsString() {
		return date.getDateAsString();
	}
	public GtfsCalendarExceptionType getExceptionType() {
		return exceptionType;
	}
	public int getExceptionTypeCode() {
		return exceptionType.getCode();
	}
	
	// Setters
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public void setDate(GtfsDate date) {
		this.date = date;
	}
	public void setDate(String datestring) {
		this.date.setDate(datestring);
	}
	public void setExceptionType(GtfsCalendarExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}
	public void setExceptionType(int exceptionTypeCode) {
		if (GtfsCalendarExceptionType.getTypeByCode(exceptionTypeCode) != null) {
			this.exceptionType = GtfsCalendarExceptionType.getTypeByCode(exceptionTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS calendar exception type", 
							exceptionTypeCode));
		}
	}
	
	
	
}
