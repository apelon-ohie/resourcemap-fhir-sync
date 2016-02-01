package com.apelon.config;

public class Config {
	
	private static String fhirServer = "http://fhir.ext.apelon.com:8081/dtsserverws/fhir";
	private static String fhirUser = "dtsadminuser";
	private static String fhirPassword = "dtsadmin";
	
	
	private static String rmServer = "http://resourcemap.instedd.org";
	private static String rmUser = "vkaloidis@apelon.com";
	private static String rmPassword = "apelon123";
	
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

}
