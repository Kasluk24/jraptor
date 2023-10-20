package ch.weinetz.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.weinetz.jraptor.utils.GtfsImport;

public class GtfsCalendarDate implements GtfsObject {
	// Fields
	private String serviceId;
	private GtfsDate date;
	private GtfsCalendarExceptionType exceptionType;
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapGetters = createGetterMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = "calendar_dates";
	
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
	public String getExceptionTypeCodeAsString() {
		return String.valueOf(exceptionType.getCode());
	}
	public Map<String, String> getMapSetters() {
		return mapSetters;
	}
	public Map<String, String> getMapGetters() {
		return mapGetters;
	}
	public Map<String, String> getMapSqliteTypes() {
		return mapSqliteTypes;
	}
	public String getSqlTableName() {
		return sqlTableName;
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

	@Override
	public Method[] getOrderedSetterArray(String[] gtfsHeader) {
		Class<GtfsCalendarDate> classObject = GtfsCalendarDate.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}
	@Override
	public Method[] getOrderedGetterArray(String[] gtfsHeader) {
		Class<GtfsCalendarDate> classObject = GtfsCalendarDate.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapGetters, gtfsHeader);
	}
	
	// Private static methods
	private static Map<String, String> createSetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("service_id", "setServiceId");
		tempFields.put("date", "setDate");
		tempFields.put("exception_type", "setExceptionType");
		return tempFields;
	}
	private static Map<String, String> createGetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("service_id", "getServiceId");
		tempFields.put("date", "getDateAsString");
		tempFields.put("exception_type", "getExceptionTypeCodeAsString");
		return tempFields;
	}
	private static Map<String, String> createSqlTypeMap() {
		Map<String, String> tempTypes = new HashMap<>();
		tempTypes.put("service_id", "TEXT");
		tempTypes.put("date", "TEXT");
		tempTypes.put("exception_type", "INT");
		return tempTypes;
	}
}
