package ch.weinetz.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.weinetz.jraptor.utils.GtfsImport;

public class GtfsStop implements GtfsTableData {
	// Fields
	private String stopId;
	private String stopCode;
	private String stopName;
	private String stopDesc;
	private Double stopLat;
	private Double stopLon;
	private GtfsLocationType locationType;
	private String parentStation;
	private String stopTimezone;
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapGetters = createGetterMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = "stops";
	public static final String gtfsFileName = "stops.txt";
	
	// Constructor
	public GtfsStop() {};
	
	public GtfsStop(String stopId, String stopCode, String stopName, String stopDesc, Double stopLat, Double stopLon,
			GtfsLocationType locationType, String parentStation, String stopTimezone) {
		this.stopId = stopId;
		this.stopCode = stopCode;
		this.stopName = stopName;
		this.stopDesc = stopDesc;
		this.stopLat = stopLat;
		this.stopLon = stopLon;
		this.locationType = locationType;
		this.parentStation = parentStation;
		this.stopTimezone = stopTimezone;
	}
	// Only Strings
	public GtfsStop(String stopId, String stopCode, String stopName, String stopDesc, String stopLat, String stopLon,
			String locationType, String parentStation, String stopTimezone) {
		this.stopId = stopId;
		this.stopCode = stopCode;
		this.stopName = stopName;
		this.stopDesc = stopDesc;
		this.stopLat = Double.valueOf(stopLat);
		this.stopLon = Double.valueOf(stopLon);
		setLocationType(locationType.isBlank() ? 0 : Integer.valueOf(locationType));
		this.parentStation = parentStation;
		this.stopTimezone = stopTimezone;
	}
	
	// Getters
	public String getStopId() {
		return stopId;
	}
	public String getStopCode() {
		return stopCode;
	}
	public String getStopName() {
		return stopName;
	}
	public String getStopDesc() {
		return stopDesc;
	}
	public Double getStopLat() {
		return stopLat;
	}
	public String getStopLatAsString() {
		return String.valueOf(stopLat);
	}
	public Double getStopLon() {
		return stopLon;
	}
	public String getStopLonAsString() {
		return String.valueOf(stopLon);
	}
	public GtfsLocationType getLocationType() {
		return locationType;
	}
	public int getLocationTypeCode() {
		return locationType.getCode();
	}
	public String getLocationTypeCodeAsString() {
		return String.valueOf(locationType.getCode());
	}
	public String getParentStation() {
		return parentStation;
	}
	public String getStopTimezone() {
		return stopTimezone;
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
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public void setStopDesc(String stopDesc) {
		this.stopDesc = stopDesc;
	}
	public void setStopLat(Double stopLat) {
		this.stopLat = stopLat;
	}
	public void setStopLat(String stopLat) {
		this.stopLat = Double.valueOf(stopLat);
	}
	public void setStopLon(Double stopLon) {
		this.stopLon = stopLon;
	}
	public void setStopLon(String stopLon) {
		this.stopLon = Double.valueOf(stopLon);
	}
	public void setLocationType(GtfsLocationType locationType) {
		this.locationType = locationType;
	}
	public void setLocationType(int locationTypeCode) {
		if (GtfsLocationType.getTypeByCode(locationTypeCode) != null) {
			this.locationType = GtfsLocationType.getTypeByCode(locationTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS location type", 
							locationTypeCode));
		}
	}
	public void setLocationType(String locationTypeCode) {
		if (locationTypeCode.isBlank()) {
			setLocationType(0);
		} else {
			setLocationType(Integer.valueOf(locationTypeCode));
		}
	}
	public void setParentStation(String parentStation) {
		this.parentStation = parentStation;
	}
	public void setStopTimezone(String stopTimezone) {
		this.stopTimezone = stopTimezone;
	}
		
	@Override
	public String toString() {
		return "GtfsStop [stopId=" + stopId + ", stopCode=" + stopCode + ", stopName=" + stopName + ", stopDesc="
				+ stopDesc + ", stopLat=" + stopLat + ", stopLon=" + stopLon + ", locationType=" + locationType
				+ ", parentStation=" + parentStation + ", stopTimezone=" + stopTimezone + "]";
	}
	@Override
	public Method[] getOrderedSetterArray(String[] gtfsHeader) {
		Class<GtfsStop> classObject = GtfsStop.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}
	@Override
	public Method[] getOrderedGetterArray(String[] gtfsHeader) {
		Class<GtfsStop> classObject = GtfsStop.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapGetters, gtfsHeader);
	}
	@Override
	public boolean equals(Object object) {
		if (!object.getClass().equals(GtfsStop.class)) {
			return false;
		} else if (((GtfsStop) object).getStopId().equals(this.stopId)) {
			return true;
		} else {
			return false;
		}
	}	
	
	// Private static methods
	private static Map<String, String> createSetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("stop_id", "setStopId");
		tempFields.put("stop_code", "setStopCode");
		tempFields.put("stop_name", "setStopName");
		tempFields.put("stop_desc", "setStopDesc");
		tempFields.put("stop_lat", "setStopLat");
		tempFields.put("stop_lon", "setStopLon");
		tempFields.put("location_type", "setLocationType");
		tempFields.put("parent_station", "setParentStation");
		tempFields.put("stop_timezone", "setStopTimezone");
		return tempFields;
	}
	private static Map<String, String> createGetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("stop_id", "getStopId");
		tempFields.put("stop_code", "getStopCode");
		tempFields.put("stop_name", "getStopName");
		tempFields.put("stop_desc", "getStopDesc");
		tempFields.put("stop_lat", "getStopLatAsString");
		tempFields.put("stop_lon", "getStopLonAsString");
		tempFields.put("location_type", "getLocationTypeCodeAsString");
		tempFields.put("parent_station", "getParentStation");
		tempFields.put("stop_timezone", "getStopTimezone");
		return tempFields;
	}
	private static Map<String, String> createSqlTypeMap() {
		Map<String, String> tempTypes = new HashMap<>();
		tempTypes.put("stop_id", "TEXT PRIMARY KEY");
		tempTypes.put("stop_code", "TEXT");
		tempTypes.put("stop_name", "TEXT");
		tempTypes.put("stop_desc", "TEXT");
		tempTypes.put("stop_lat", "REAL");
		tempTypes.put("stop_lon", "REAL");
		tempTypes.put("location_type", "INT");
		tempTypes.put("parent_station", "TEXT");
		tempTypes.put("stop_timezone", "TEXT");
		return tempTypes;
	}
}
