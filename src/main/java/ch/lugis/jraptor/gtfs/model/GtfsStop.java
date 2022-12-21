package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

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
	public static Map<String, String> mapSetters = createSetterMap();
	
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
	public Double getStopLon() {
		return stopLon;
	}
	public GtfsLocationType getLocationType() {
		return locationType;
	}
	public int getLocationTypeCode() {
		return locationType.getCode();
	}
	public String getParentStation() {
		return parentStation;
	}
	public String getStopTimezone() {
		return stopTimezone;
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
}
