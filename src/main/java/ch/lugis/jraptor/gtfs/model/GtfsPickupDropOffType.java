package ch.lugis.jraptor.gtfs.model;

public enum GtfsPickupDropOffType {
	REGULARLY(0),
	NO_PICKUP_DROPOFF(1),
	PHONE_AGENCY(2),
	PHONE_DRIVER(3);
	
	private final int code;

	GtfsPickupDropOffType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsPickupDropOffType getTypeByCode(int code) {
		for (GtfsPickupDropOffType type : GtfsPickupDropOffType.values()) {
			return type;
		}
		return null;
	}
	
}
