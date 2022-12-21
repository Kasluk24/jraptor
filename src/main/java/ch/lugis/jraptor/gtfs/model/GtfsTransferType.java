package ch.lugis.jraptor.gtfs.model;

public enum GtfsTransferType {
	RECOMMENDED(0),
	TIMED(1),
	MIN_TIME(2),
	IMPOSSIBLE(3),
	IN_SEAT(4),
	NO_IN_SEAT(5);
	
	private final int code;

	GtfsTransferType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsTransferType getTypeByCode(int code) {
		for (GtfsTransferType type : GtfsTransferType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
}
