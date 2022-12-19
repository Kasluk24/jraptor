package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

public class GtfsAgency implements GtfsTableData {
	// Fields
	private String agencyId;
	private String agencyName;
	private String agencyUrl;
	private String agencyTimezone;
	private String agencyLang;
	private String agencyPhone;
	private String agencyEmail;
	public static final Map<String, String> mapSetters = createSettersMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = "agency";
		
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
	
	@Override
	public String toString() {
		return "GtfsAgency [agencyId=" + agencyId + ", agencyName=" + agencyName + ", agencyUrl=" + agencyUrl
				+ ", agencyTimezone=" + agencyTimezone + ", agencyLang=" + agencyLang + ", agencyPhone=" + agencyPhone
				+ ", agencyEmail=" + agencyEmail + "]";
	}
	
	@Override
	public Method[] getOrderedMethodArray(String[] gtfsHeader) {
		Class<GtfsAgency> classObject = GtfsAgency.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}	
	
	// Private methods
	private static Map<String, String> createSettersMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("agency_id", "setAgencyId");
		tempFields.put("agency_name", "setAgencyName");
		tempFields.put("agency_url", "setAgencyUrl");
		tempFields.put("agency_timezone", "setAgencyTimezone");
		tempFields.put("agency_lang", "setAgencyLang");
		tempFields.put("agency_phone", "setAgencyPhone");
		tempFields.put("agency_email", "setAgencyEmail");
		return tempFields;
	}
	private static Map<String, String> createSqlTypeMap() {
		Map<String, String> tempTypes = new HashMap<>();
		tempTypes.put("agency_id", "TEXT");
		tempTypes.put("agency_name", "TEXT");
		tempTypes.put("agency_url", "TEXT");
		tempTypes.put("agency_timezone", "TEXT");
		tempTypes.put("agency_lang", "TEXT");
		tempTypes.put("agency_phone", "TEXT");
		tempTypes.put("agency_email", "TEXT");
		return tempTypes;
	}
}
