package ch.lugis.jraptor.gtfs.model;

public class GtfsStopTime {
	// Fields
	private String tripId;
	private GtfsTime arrivalTime = new GtfsTime();
	private GtfsTime departureTime = new GtfsTime();
	private String stopId;
	private String stopSequence;
	private String stopHeadsign;
	private GtfsPickupDropOffType pickupType;
	private GtfsPickupDropOffType dropOffType;
	private Double shapeDistTraveled;
	
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
		setDropoffType(Integer.valueOf(dropoffType));
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
	public void setDropoffType(GtfsPickupDropOffType dropoffType) {
		this.dropOffType = dropoffType;
	}
	public void setDropoffType(int dropoffTypeCode) {
		if (GtfsPickupDropOffType.getTypeByCode(dropoffTypeCode) != null) {
			this.dropOffType = GtfsPickupDropOffType.getTypeByCode(dropoffTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS pickup type", 
							dropoffTypeCode));
		}
	}
	public void setShapeDistTraveled(Double shapeDistTraveled) {
		this.shapeDistTraveled = shapeDistTraveled;
	};
	
	// Public static methods
	public static int[] mapFields(String[] headerValues) {
		int[] valueOrder = new int[9];
		int counter = 0;
		for (String column : headerValues) {
			switch (column) {
				case "trip_id": valueOrder[0] = counter; break;
				case "arrival_time": valueOrder[1] = counter; break;
				case "departure_time": valueOrder[2] = counter; break;
				case "stop_id": valueOrder[3] = counter; break;
				case "stop_sequence": valueOrder[4] = counter; break;
				case "stop_headsign": valueOrder[5] = counter; break;
				case "pickup_type": valueOrder[6] = counter; break;
				case "drop_off_type": valueOrder[7] = counter; break;
				case "shape_dist_traveled": valueOrder[8] = counter; break;
			}
			counter++;
		}
		return valueOrder;
	}
}
