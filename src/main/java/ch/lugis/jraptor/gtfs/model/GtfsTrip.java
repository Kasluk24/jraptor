package ch.lugis.jraptor.gtfs.model;

public class GtfsTrip {
	// Fields
	private String routeId;
	private String serviceId;
	private String tripId;
	private String tripHeadsign;
	private String tripShortName;
	private GtfsDirectionId directionId;
	private String blockId;
	private String shapeId;
	
	// Constructor
	public GtfsTrip() {};
	
	public GtfsTrip(String routeId, String serviceId, String tripId, String tripHeadsign, String tripShortName,
			GtfsDirectionId directionId, String blockId, String shapeId) {
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.tripId = tripId;
		this.tripHeadsign = tripHeadsign;
		this.tripShortName = tripShortName;
		this.directionId = directionId;
		this.blockId = blockId;
		this.shapeId = shapeId;
	}
	// Only Strings
	public GtfsTrip(String routeId, String serviceId, String tripId, String tripHeadsign, String tripShortName,
			String directionId, String blockId, String shapeId) {
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.tripId = tripId;
		this.tripHeadsign = tripHeadsign;
		this.tripShortName = tripShortName;
		setDirectionId(Integer.valueOf(directionId));
		this.blockId = blockId;
		this.shapeId = shapeId;
	}
	
	// Getters
	public String getRouteId() {
		return routeId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public String getTripId() {
		return tripId;
	}
	public String getTripHeadsign() {
		return tripHeadsign;
	}
	public String getTripShortName() {
		return tripShortName;
	}
	public GtfsDirectionId getDirectionId() {
		return directionId;
	}
	public int getDirectionIdCode() {
		return directionId.getCode();
	}
	public String getBlockId() {
		return blockId;
	}
	public String getShapeId() {
		return shapeId;
	}
	
	// Setters
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public void setTripHeadsign(String tripHeadsign) {
		this.tripHeadsign = tripHeadsign;
	}
	public void setTripShortName(String tripShortName) {
		this.tripShortName = tripShortName;
	}
	public void setDirectionId(GtfsDirectionId directionId) {
		this.directionId = directionId;
	}
	public void setDirectionId(int directionIdCode) {
		if (GtfsDirectionId.getIdByCode(directionIdCode) != null) {
			this.directionId = GtfsDirectionId.getIdByCode(directionIdCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS direction id", 
							directionIdCode));
		}
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}
	
	// Public static methods
	public static int[] mapFields(String[] headerValues) {
		int[] valueOrder = new int[8];
		int counter = 0;
		for (String column : headerValues) {
			switch (column) {
				case "route_id": valueOrder[0] = counter; break;
				case "service_id": valueOrder[1] = counter; break;
				case "trip_id": valueOrder[2] = counter; break;
				case "trip_headsign": valueOrder[3] = counter; break;
				case "trip_short_name": valueOrder[4] = counter; break;
				case "direction_id": valueOrder[5] = counter; break;
				case "block_id": valueOrder[6] = counter; break;
				case "shape_id": valueOrder[7] = counter; break;
			}
			counter++;
		}
		return valueOrder;
	}
}
