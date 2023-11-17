package ch.weinetz.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.weinetz.jraptor.utils.GtfsImport;

public class GtfsStopTime implements GtfsTableData{
	// Fields
	private String tripId;
	private GtfsTime arrivalTime;
	private GtfsTime departureTime;
	private String stopId;
	private Integer stopSequence;
	private String stopHeadsign;
	private GtfsPickupDropOffType pickupType;
	private GtfsPickupDropOffType dropOffType;
	private Double shapeDistTraveled;
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapGetters = createGetterMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = "stop_times";
	public static final String gtfsFileName = "stop_times.txt";
	
	// Constructor
	public GtfsStopTime() {}

	public GtfsStopTime(String tripId, GtfsTime arrivalTime, GtfsTime departureTime, String stopId, Integer stopSequence,
			String stopHeadsign, GtfsPickupDropOffType pickupType, GtfsPickupDropOffType dropoffType,
			Double shapeDistTraveled) {
		this.tripId = tripId;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopId = stopId;
		this.stopSequence = stopSequence;
		this.stopHeadsign = stopHeadsign;
		this.pickupType = pickupType;
		this.dropOffType = dropoffType;
		this.shapeDistTraveled = shapeDistTraveled;
	}
	// Only Strings
	public GtfsStopTime(String tripId, String arrivalTime, String departureTime, String stopId, String stopSequence,
			String stopHeadsign, String pickupType, String dropoffType,
			String shapeDistTraveled) {
		this.tripId = tripId;
		setArrivalTime(arrivalTime);
		setDepartureTime(departureTime);
		this.stopId = stopId;
		this.stopSequence = Integer.valueOf(stopSequence);
		this.stopHeadsign = stopHeadsign;
		setPickupType(Integer.valueOf(pickupType));
		setDropOffType(Integer.valueOf(dropoffType));
		this.shapeDistTraveled = Double.valueOf(shapeDistTraveled);
	}
	
	// Getters
	public String getTripId() {
		return tripId;
	}
	public GtfsTime getArrivalTime() {
		return arrivalTime;
	}
	public String getArrivalTimeAsString() {
		return arrivalTime.getTimeAsString();
	}
	public GtfsTime getDepartureTime() {
		return departureTime;
	}
	public String getDepartureTimeAsString() {
		return departureTime.getTimeAsString();
	}
	public String getStopId() {
		return stopId;
	}
	public Integer getStopSequence() {
		return stopSequence;
	}
	public String getStopSequenceAsString() {
		return String.valueOf(stopSequence);
	}
	public String getStopHeadsign() {
		return stopHeadsign;
	}
	public GtfsPickupDropOffType getPickupType() {
		return pickupType;
	}
	public int getPickupTypeCode() {
		return pickupType.getCode();
	}
	public String getPickupTypeCodeAsString() {
		return String.valueOf(pickupType.getCode());
	}
	public GtfsPickupDropOffType getDropOffType() {
		return dropOffType;
	}
	public int getDropOffTypeCode() {
		return dropOffType.getCode();
	}
	public String getDropOffTypeCodeAsString() {
		return String.valueOf(dropOffType.getCode());
	}
	public Double getShapeDistTraveled() {
		return shapeDistTraveled;
	}
	public String getShapeDistTraveledAsString() {
		return String.valueOf(shapeDistTraveled);
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
	public void setArrivalTime(GtfsTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public void setArrivalTime(String timestring) {
		this.arrivalTime = new GtfsTime(timestring);
	}
	public void setDepartureTime(GtfsTime departureTime) {
		this.departureTime = departureTime;
	}
	public void setDepartureTime(String timestring) {
		this.departureTime = new GtfsTime(timestring);
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public void setStopSequence(Integer stopSequence) {
		this.stopSequence = stopSequence;
	}
	public void setStopSequence(String stopSequence) {
		this.stopSequence = Integer.valueOf(stopSequence);
	}
	public void setStopHeadsign(String stopHeadsign) {
		this.stopHeadsign = stopHeadsign;
	}
	public void setPickupType(GtfsPickupDropOffType pickupType) {
		this.pickupType = pickupType;
	}
	public void setPickupType(int pickupTypeCode) {
		if (GtfsPickupDropOffType.getTypeByCode(pickupTypeCode) != null) {
			this.pickupType = GtfsPickupDropOffType.getTypeByCode(pickupTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS pickup type", 
							pickupTypeCode));
		}
	}
	public void setPickupType(String pickupTypeCode) {
		setPickupType(Integer.valueOf(pickupTypeCode));
	}
	public void setDropOffType(GtfsPickupDropOffType dropOffType) {
		this.dropOffType = dropOffType;
	}
	public void setDropOffType(int dropOffTypeCode) {
		if (GtfsPickupDropOffType.getTypeByCode(dropOffTypeCode) != null) {
			this.dropOffType = GtfsPickupDropOffType.getTypeByCode(dropOffTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS drop off type", 
							dropOffTypeCode));
		}
	}
	public void setDropOffType(String dropOffTypeCode) {
		setDropOffType(Integer.valueOf(dropOffTypeCode));
	}
	public void setShapeDistTraveled(Double shapeDistTraveled) {
		this.shapeDistTraveled = shapeDistTraveled;
	}
	public void setShapeDistTraveled(String shapeDistTraveled) {
		this.shapeDistTraveled = Double.valueOf(shapeDistTraveled);
	}
	
	@Override
	public String toString() {
		return "GtfsStopTime [tripId=" + tripId + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime
				+ ", stopId=" + stopId + ", stopSequence=" + stopSequence + ", stopHeadsign=" + stopHeadsign
				+ ", pickupType=" + pickupType + ", dropOffType=" + dropOffType + ", shapeDistTraveled="
				+ shapeDistTraveled + "]";
	}

	@Override
	public Method[] getOrderedSetterArray(String[] gtfsHeader) {
		Class<GtfsStopTime> classObject = GtfsStopTime.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}
	@Override
	public Method[] getOrderedGetterArray(String[] gtfsHeader) {
		Class<GtfsStopTime> classObject = GtfsStopTime.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapGetters, gtfsHeader);
	}	
	
	// Private static methods
	private static Map<String, String> createSetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("trip_id", "setTripId");
		tempFields.put("arrival_time", "setArrivalTime");
		tempFields.put("departure_time", "setDepartureTime");
		tempFields.put("stop_id", "setStopId");
		tempFields.put("stop_sequence", "setStopSequence");
		tempFields.put("stop_headsign", "setStopHeadsign");
		tempFields.put("pickup_type", "setPickupType");
		tempFields.put("drop_off_type", "setDropOffType");
		tempFields.put("shape_dist_traveled", "setShapeDistTraveled");
		return tempFields;
	}
	private static Map<String, String> createGetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("trip_id", "getTripId");
		tempFields.put("arrival_time", "getArrivalTimeAsString");
		tempFields.put("departure_time", "getDepartureTimeAsString");
		tempFields.put("stop_id", "getStopId");
		tempFields.put("stop_sequence", "getStopSequenceAsString");
		tempFields.put("stop_headsign", "getStopHeadsign");
		tempFields.put("pickup_type", "getPickupTypeCodeAsString");
		tempFields.put("drop_off_type", "getDropOffTypeCodeAsString");
		tempFields.put("shape_dist_traveled", "getShapeDistTraveledAsString");
		return tempFields;
	}
	private static Map<String, String> createSqlTypeMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("trip_id", "TEXT");
		tempFields.put("arrival_time", "TEXT");
		tempFields.put("departure_time", "TEXT");
		tempFields.put("stop_id", "TEXT");
		tempFields.put("stop_sequence", "INT");
		tempFields.put("stop_headsign", "TEXT");
		tempFields.put("pickup_type", "INT");
		tempFields.put("drop_off_type", "INT");
		tempFields.put("shape_dist_traveled", "REAL");
		return tempFields;
	}
}
