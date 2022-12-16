package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

public class GtfsCalendarDate {
	// Fields
	private String serviceId;
	private GtfsDate date;
	private GtfsCalendarExceptionType exceptionType;
	public static Map<String, String> mapSetters = new HashMap<>();
	
	static {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("service_id", "setServiceId");
		tempFields.put("date", "setDate");
		tempFields.put("exception_type", "setExceptionType");
		mapSetters = Collections.unmodifiableMap(tempFields);
	}
	
	// Constructor
	public GtfsCalendarDate() {}

	public GtfsCalendarDate(String serviceId, GtfsDate date, GtfsCalendarExceptionType exceptionType) {
		this.serviceId = serviceId;
		this.date = date;
		this.exceptionType = exceptionType;
	}
	public GtfsCalendarDate(String serviceId, String date, int exceptionType) {
		this.serviceId = serviceId;
		setDate(date);
		if (GtfsCalendarExceptionType.getTypeByCode(exceptionType) != null) {
			this.exceptionType = GtfsCalendarExceptionType.getTypeByCode(exceptionType);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS calendar exception type", 
							exceptionType));
		}
	}
	// Only Strings
	public GtfsCalendarDate(String serviceId, String date, String exceptionType) {
		this.serviceId = serviceId;
		setDate(date);
		setExceptionType(Integer.valueOf(exceptionType));
	}

	// Getters
	public String getServiceId() {
		return serviceId;
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
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public void setDate(GtfsDate date) {
		this.date = date;
	}
	public void setDate(String datestring) {
		this.date = new GtfsDate(datestring);
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
	public void setExceptionType(String exceptionTypeCode) {
		setExceptionType(Integer.valueOf(exceptionTypeCode));
	}
	
	@Override
	public String toString() {
		return "GtfsCalendarDate [serviceId=" + serviceId + ", date=" + date + ", exceptionType=" + exceptionType + "]";
	}

	// Public static methods
	public static Method[] getOrderedMethodArray(String[] gtfsHeader) {
		Class<GtfsCalendarDate> classObject = GtfsCalendarDate.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}	
}
