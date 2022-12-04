package ch.lugis.jraptor.gtfs.model;

public enum GtfsPickupDropoffType {
	REGULARLY(0),
	NO_PICKUP_DROPOFF(1),
	PHONE_AGENCY(2),
	PHONE_DRIVER(3);
	
	private final int code;

	GtfsPickupDropoffType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsPickupDropoffType getTypeByCode(int code) {
		for (GtfsPickupDropoffType type : GtfsPickupDropoffType.values()) {
			return type;
		}
		return null;
	}
	
}
