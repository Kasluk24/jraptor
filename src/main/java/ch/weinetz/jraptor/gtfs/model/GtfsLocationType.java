package ch.weinetz.jraptor.gtfs.model;

public enum GtfsLocationType {
	STOP_PLATFORM(0),
	STATION(1),
	ENTRANCE_EXIT(2),
	GENERIC_NODE(3),
	BOARDING_AREA(4);
	
	private final int code;

	GtfsLocationType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsLocationType getTypeByCode(int code) {
		for (GtfsLocationType type : GtfsLocationType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
	
}
