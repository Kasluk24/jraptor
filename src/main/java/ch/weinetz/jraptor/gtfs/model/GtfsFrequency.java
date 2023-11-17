package ch.weinetz.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.weinetz.jraptor.utils.GtfsImportUtils;

public class GtfsFrequency implements GtfsTableData {
	// Fields
	private String tripId;
	private GtfsTime startTime;
	private GtfsTime endTime;
	private Integer headwaySecs;
	private GtfsFrequenciesExactTimesType exactTime;
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapGetters = createGetterMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = "frequencies";
	public static final String gtfsFileName = "frequencies.txt";
	
	// Constructor
	public GtfsFrequency() {}

	public GtfsFrequency(String tripId, GtfsTime startTime, GtfsTime endTime, Integer headwaySecs,
			GtfsFrequenciesExactTimesType exactTime) {
		this.tripId = tripId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.headwaySecs = headwaySecs;
		this.exactTime = exactTime;
	}
	// Only Strings
	public GtfsFrequency(String tripId, String startTime, String endTime, String headwaySecs,
			String exactTime) {
		this.tripId = tripId;
		setStartTime(startTime);
		setEndTime(endTime);
		this.headwaySecs = Integer.valueOf(headwaySecs);
		setExactTime(Integer.valueOf(exactTime));
	}

	// Getters
	public String getTripId() {
		return tripId;
	}
	public GtfsTime getStartTime() {
		return startTime;
	}
	public String getStartTimeAsString() {
		return startTime.getTimeAsString();
	}
	public GtfsTime getEndTime() {
		return endTime;
	}
	public String getEndTimeAsString() {
		return endTime.getTimeAsString();
	}
	public Integer getHeadwaySecs() {
		return headwaySecs;
	}
	public String getHeadwaySecsAsString() {
		return String.valueOf(headwaySecs);
	}
	public GtfsFrequenciesExactTimesType getExactTime() {
		return exactTime;
	}
	public int getExactTimeCode() {
		return exactTime.getCode();
	}
	public String getExactTimeCodeAsString() {
		return String.valueOf(exactTime.getCode());
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
	public String getGtfsFileName() {
		return gtfsFileName;
	}

	// Setters
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public void setStartTime(GtfsTime startTime) {
		this.startTime = startTime;
	}
	public void setStartTime(String timestring) {
		this.startTime = new GtfsTime(timestring);
	}
	public void setEndTime(GtfsTime endTime) {
		this.endTime = endTime;
	}
	public void setEndTime(String timestring) {
		this.endTime = new GtfsTime(timestring);
	}
	public void setHeadwaySecs(Integer headwaySecs) {
		this.headwaySecs = headwaySecs;
	}
	public void setHeadwaySecs(String headwaySecs) {
		this.headwaySecs = Integer.valueOf(headwaySecs);
	}
	public void setExactTime(GtfsFrequenciesExactTimesType exactTime) {
		this.exactTime = exactTime;
	}
	public void setExactTime(int exactTimeCode) {
		if (GtfsFrequenciesExactTimesType.getTypeByCode(exactTimeCode) != null) {
			this.exactTime = GtfsFrequenciesExactTimesType.getTypeByCode(exactTimeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS exact time type", 
							exactTimeCode));
		}
	}
	public void setExactTime(String exactTime) {
		setExactTime(Integer.valueOf(exactTime));
	}
	
	@Override
	public String toString() {
		return "GtfsFrequency [tripId=" + tripId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", headwaySecs=" + headwaySecs + ", exactTime=" + exactTime + "]";
	}
	@Override
	public Method[] getOrderedSetterArray(String[] gtfsHeader) {
		Class<GtfsFrequency> classObject = GtfsFrequency.class;
		return GtfsImportUtils.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}
	@Override
	public Method[] getOrderedGetterArray(String[] gtfsHeader) {
		Class<GtfsFrequency> classObject = GtfsFrequency.class;
		return GtfsImportUtils.createOrderedMethodArray(classObject, mapSetters, gtfsHeader);
	}
	
	// Private static methods
	private static Map<String, String> createSetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("trip_id", "setTripId");
		tempFields.put("start_time", "setStartTime");
		tempFields.put("end_time", "setEndTime");
		tempFields.put("headway_secs", "setHeadwaySecs");
		tempFields.put("exact_time", "setExactTime");
		return tempFields;
	}
	private static Map<String, String> createGetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("trip_id", "getTripId");
		tempFields.put("start_time", "getStartTimeAsString");
		tempFields.put("end_time", "getEndTimeAsString");
		tempFields.put("headway_secs", "getHeadwaySecsAsString");
		tempFields.put("exact_time", "getExactTimeCodeAsString");
		return tempFields;
	}
	private static Map<String, String> createSqlTypeMap() {
		Map<String, String> tempTypes = new HashMap<>();
		tempTypes.put("trip_id", "TEXT");
		tempTypes.put("start_time", "TEXT");
		tempTypes.put("end_time", "TEXT");
		tempTypes.put("headway_secs", "INT");
		tempTypes.put("exact_time", "INT");
		return tempTypes;
	}
}
