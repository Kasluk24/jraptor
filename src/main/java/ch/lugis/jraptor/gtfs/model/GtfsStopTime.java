package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

public class GtfsStopTime implements GtfsTableData{
	// Fields
	private String tripId;
	private GtfsTime arrivalTime;
	private GtfsTime departureTime;
	private String stopId;
	private String stopSequence;
	private String stopHeadsign;
	private GtfsPickupDropOffType pickupType;
	private GtfsPickupDropOffType dropOffType;
	private Double shapeDistTraveled;
	public static final Map<String, String> mapSetters = createSettersMap();
	
	private static Map<String, String> createSettersMap() {
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
	
	// Constructor
	public GtfsStopTime() {}

	public GtfsStopTime(String tripId, GtfsTime arrivalTime, GtfsTime departureTime, String stopId, String stopSequence,
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
		this.stopSequence = stopSequence;
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
	public String getStopSequence() {
		return stopSequence;
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
	public GtfsPickupDropOffType getDropoffType() {
		return dropOffType;
	}
	public int getDropoffTypeCode() {
		return dropOffType.getCode();
	}
	public Double getShapeDistTraveled() {
		return shapeDistTraveled;
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
	public void setStopSequence(String stopSequence) {
		this.stopSequence = stopSequence;
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
	public Method[] getOrderedMethodArray(String[] gtfsHeader) {
		Class<GtfsStopTime> classObject = GtfsStopTime.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}	
}
