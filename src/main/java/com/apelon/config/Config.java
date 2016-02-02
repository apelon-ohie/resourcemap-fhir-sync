package com.apelon.config;

public class Config {

	//FHIR Credentials
	private static String fhirServer = "http://fhir.ext.apelon.com:8081/dtsserverws/fhir";
	private static String fhirUser = "dtsadminuser";
	private static String fhirPassword = "dtsadmin";
	
	// Resource Map Credentials
	private static String rmServer = "http://resourcemap.instedd.org";
	private static String rmUser = "vkaloidis@apelon.com";
	private static String rmPassword = "apelon123";

	// FHIR Value-Sets
	private static String valueset_tanzania_regions = "RegionsOfTanzania";
	private static String valueset_tanzania_facilities = "RegionsOfTanzania";
	private static String valueset_snomed_facility_types = "valueset-c80-facilitycodes";
	
	public static String getRmUser() {
		return rmUser;
	}
	public static String getRmPassword() {
		return rmPassword;
	}
	
	public static String getRmServer() {
		return rmServer;
	}
	
	public static String getFhirUser() {
		return fhirUser;
	}
	public static String getFhirPassword() {
		return fhirPassword;
	}
	
	public static String getFhirServer() {
		return fhirServer;
	}

	public static String getValuesetTanzaniaRegions() { return valueset_tanzania_regions;}
	public static String getValuesetTanzanianFacilities() { return valueset_tanzania_facilities;}
	public static String getValuesetSnomedFacilityTypes() { return valueset_snomed_facility_types;}

}
