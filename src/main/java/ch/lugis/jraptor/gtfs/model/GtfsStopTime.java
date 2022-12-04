package ch.lugis.jraptor.gtfs.model;

public class GtfsStopTime {
	// Fields
	private String tripId;
	private GtfsTime arrivalTime;
	private GtfsTime departureTime;
	private String stopId;
	private String stopSequence;
	private String stopHeadsign;
	private GtfsPickupDropoffType pickupType;
	private GtfsPickupDropoffType dropoffType;
	private Double shapeDistTraveled;
	
	// Constructor
	public GtfsStopTime() {}

	public GtfsStopTime(String tripId, GtfsTime arrivalTime, GtfsTime departureTime, String stopId, String stopSequence,
			String stopHeadsign, GtfsPickupDropoffType pickupType, GtfsPickupDropoffType dropoffType,
			Double shapeDistTraveled) {
		this.tripId = tripId;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopId = stopId;
		this.stopSequence = stopSequence;
		this.stopHeadsign = stopHeadsign;
		this.pickupType = pickupType;
		this.dropoffType = dropoffType;
		this.shapeDistTraveled = shapeDistTraveled;
	}
	
	// Getters
	public String getTripId() {
		return tripId;
	}
	public GtfsTime getArrivalTime() {
		return arrivalTime;
	}
	public GtfsTime getDepartureTime() {
		return departureTime;
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
	public GtfsPickupDropoffType getPickupType() {
		return pickupType;
	}
	public int getPickupTypeCode() {
		return pickupType.getCode();
	}
	public GtfsPickupDropoffType getDropoffType() {
		return dropoffType;
	}
	public int getDropoffTypeCode() {
		return dropoffType.getCode();
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
	public void setDepartureTime(GtfsTime departureTime) {
		this.departureTime = departureTime;
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
	public void setPickupType(GtfsPickupDropoffType pickupType) {
		this.pickupType = pickupType;
	}
	public void setPickupType(int pickupTypeCode) {
		if (GtfsPickupDropoffType.getTypeByCode(pickupTypeCode) != null) {
			this.pickupType = GtfsPickupDropoffType.getTypeByCode(pickupTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS pickup type", 
							pickupTypeCode
						)
					);
		}
	}
	public void setDropoffType(GtfsPickupDropoffType dropoffType) {
		this.dropoffType = dropoffType;
	}
	public void setDropoffType(int dropoffTypeCode) {
		if (GtfsPickupDropoffType.getTypeByCode(dropoffTypeCode) != null) {
			this.dropoffType = GtfsPickupDropoffType.getTypeByCode(dropoffTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS pickup type", 
							dropoffTypeCode
						)
					);
		}
	}
	public void setShapeDistTraveled(Double shapeDistTraveled) {
		this.shapeDistTraveled = shapeDistTraveled;
	};
}
