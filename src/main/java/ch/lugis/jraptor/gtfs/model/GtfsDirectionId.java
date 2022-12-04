package ch.lugis.jraptor.gtfs.model;

public enum GtfsDirectionId {
	DIRECTION_IN(0),
	DIRECTION_OUT(1);
	
	private final int code;

	GtfsDirectionId(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsDirectionId getIdByCode(int code) {
		for (GtfsDirectionId id : GtfsDirectionId.values()) {
			return id;
		}
		return null;
	}
}
