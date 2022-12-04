package ch.lugis.jraptor.gtfs.model;

public enum GtfsCalendarExceptionType {
	ADDED(1),
	REMOVED(2);
	
	private final int code;

	GtfsCalendarExceptionType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsCalendarExceptionType getTypeByCode(int code) {
		for (GtfsCalendarExceptionType type : GtfsCalendarExceptionType.values()) {
			return type;
		}
		return null;
	}
}
