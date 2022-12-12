package ch.lugis.jraptor.gtfs.model;

public class GtfsAgency {
	// Fields
	private String agencyId;
	private String agencyName;
	private String agencyUrl;
	private String agencyTimezone;
	private String agencyLang;
	private String agencyPhone;
	private String agencyEmail;
	
	// Constructor
	public GtfsAgency() {};
	// Only Strings
	public GtfsAgency(String agencyId, String agencyName, String agencyUrl, String agencyTimezone, String agencyLang,
			String agencyPhone, String agencyEmail) {
		this.agencyId = agencyId;
		this.agencyName = agencyName;
		this.agencyUrl = agencyUrl;
		this.agencyTimezone = agencyTimezone;
		this.agencyLang = agencyLang;
		this.agencyPhone = agencyPhone;
		this.agencyEmail = agencyEmail;
	}
	
	// Getters
	public String getAgencyId() {
		return agencyId;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public String getAgencyUrl() {
		return agencyUrl;
	}
	public String getAgencyTimezone() {
		return agencyTimezone;
	}
	public String getAgencyLang() {
		return agencyLang;
	}
	public String getAgencyPhone() {
		return agencyPhone;
	}
	public String getAgencyEmail() {
		return agencyEmail;
	}
	
	// Setters
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public void setAgencyUrl(String agencyUrl) {
		this.agencyUrl = agencyUrl;
	}
	public void setAgencyTimezone(String agencyTimezone) {
		this.agencyTimezone = agencyTimezone;
	}
	public void setAgencyLang(String agencyLang) {
		this.agencyLang = agencyLang;
	}
	public void setAgencyPhone(String agencyPhone) {
		this.agencyPhone = agencyPhone;
	}
	public void setAgencyEmail(String agencyEmail) {
		this.agencyEmail = agencyEmail;
	}
	
	// Public static methods
	public static int[] mapFields(String[] headerValues) {
		int[] valueOrder = new int[7];
		int counter = 0;
		for (String column : headerValues) {
			switch (column) {
				case "agency_id": valueOrder[0] = counter; break;
				case "agency_name": valueOrder[1] = counter; break;
				case "agency_url": valueOrder[2] = counter; break;
				case "agency_timezone": valueOrder[3] = counter; break;
				case "agency_lang": valueOrder[4] = counter; break;
				case "agency_phone": valueOrder[5] = counter; break;
				case "agency_email": valueOrder[6] = counter; break;
			}
			counter++;
		}
		return valueOrder;	
	}
}
