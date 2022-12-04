package ch.lugis.jraptor.gtfs.model;

public enum GtfsSimpleRouteType {
	TRAM_STREETCAR_LIGHTRAIL(0),
	SUBWAY_METRO(1),
	RAIL(2),
	BUS(3),
	FERRY(4),
	CABLETRAM(5),
	ARIAL_LIFT(6),
	FUNICULAR(7),
	TROLLEYBUS(11),
	MONORAIL(12);
	
	private final int code;

	GtfsSimpleRouteType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsSimpleRouteType getTypeByCode(int code) {
		for (GtfsSimpleRouteType type : GtfsSimpleRouteType.values()) {
			return type;
		}
		return null;
	}
}
