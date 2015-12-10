package org.apelon.rmApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.hl7.fhir.ValueSet;
import org.hl7.fhir.ValueSetContains;

import com.apelon.demo.dts.fhir.simpleclient.util.http.HttpMessageSender;
import com.apelon.dts.fhir.translator.valueset.xml.FhirXmlTranslator;

public class rmRestScript {
	
	private static final Logger logger = Logger.getLogger(rmRestScript.class);
	private StringBuffer sb;
	
	public void addCommandParam(String c) {
		sb.append(c + System.lineSeparator());
	}
	
	public String getJsonCommand() {
		return sb.toString();
	}
	
	/**
	 * The JSON Command to update facilities layer for Resource MAP, from FHIR data
	 * TODO: Add Getters and Setters for Layer ID's
	 * @return Resource Map Update Layer JSON Command (String)
	 */
	public String updateLayer(int layerId, String valueSet) {
		sb = new StringBuffer();
		addCommandParam("{");
			addCommandParam("\"layer\":{");
				addCommandParam("\"id\":\"" + layerId + "\",");
				addCommandParam("\"name\":\"Medical Facility Information\",");
				addCommandParam("\"ord\":\"2\",");
				addCommandParam("\"fields_attributes\":{");
					addCommandParam("\"0\":{ ");
						addCommandParam("\"id\":\"13377\",");
						addCommandParam("\"name\":\"Facility Type\",");
						addCommandParam("\"code\":\"facility_type\",");
						addCommandParam("\"kind\":\"select_one\",");
						addCommandParam("\"ord\":\"1\","); 
						addCommandParam("\"layer_id\":\"" + layerId + "\",");
						addCommandParam("\"config\":{");
							addCommandParam("\"options\":{");
								int NEXT_ID = 81; //NEXT_ID 
								int count = 0;
								List<ValueSetContains> facilitiesData = this.getFhirFacilities(valueSet);
								for(ValueSetContains row : facilitiesData) {
									addCommandParam("\"" + count + "\":{ ");
										addCommandParam("\"id\":\"" + (NEXT_ID) + "\",");
										addCommandParam("\"code\":\"" + row.getCode().getValue() + "\",");
										addCommandParam("\"label\":\"" + row.getDisplay().getValue() + "\"");
									addCommandParam("}");
									//addCommandParam(count + " / " + facilitiesData.size());
									if(count != (facilitiesData.size()-1)) {
										addCommandParam(","); 
									}
									count++; NEXT_ID++;
								}
							addCommandParam("}");
						addCommandParam("}");
					addCommandParam("}");
				addCommandParam("}");
			addCommandParam("}");
		addCommandParam("}");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Gets FHIR Facilities
	 * TODO: Break out the facilities value-set to make this more generic for more JSON commands
	 * @return List of Facilities (from FHIR)
	 */
	private List<ValueSetContains> getFhirFacilities(String valueSetName) {
		try {
			
			// get parameters
			logger.debug("Getting value set from the demo server");
			String uid = "dtsadminuser";
			String pwd = "DTS-PASSWORD";
			String url = "http://fhir.ext.apelon.com:8081/dtsserverws/fhir";
			url += "/ValueSet/" + valueSetName + "/$expand";
			// create the http client
			HttpMessageSender client = new HttpMessageSender(uid, pwd);
			// send the request
			String valueSetXml = client.get(url);
			// echo the response
			logger.debug("Got XML message: \n\n" + valueSetXml + "\n\n");
			logger.debug("Doing translation");
			// parse xml into java object
			ValueSet valueSet = FhirXmlTranslator.getInstance().getValueSetFromXml(valueSetXml);
			logger.debug("Got value set object");
			// display what we got
			List<ValueSetContains> contains = valueSet.getExpansion().getContains();
			logger.debug("Got " + contains.size() + " concepts.");
			
			return contains;
     } catch (Exception exp) {
            throw new RuntimeException(exp);
     }
	}
	
	/**
	 * Takes a JSON Command and authenticates against Resource Map Server and sends Command
	 * @param command Resource JSON Command (String)
	 */
	private void updateLayer(int collectionId, int layerId, String valueSetName) {
		String command = this.updateLayer(layerId, valueSetName);
		
		HttpURLConnection conn = null;
		try {
			String base = "http://resourcemap.instedd.org";
			//String link = base + "/api/collections/1666/layers/1673.json";
			//String link = base + "/api/collections/1666/layers/1670.json";
			String link = "http://resourcemap.instedd.org/api/collections/" + collectionId + "/layers/" + layerId + ".json";
			System.out.println("Link: " + link);
			String uid = "vkaloidis@apelon.com";
			String pwd = "RESOURCEMAP-PASSWORD";

			String authenticationString = new String(Base64.encodeBase64((uid
					+ ":" + pwd).getBytes()));
			URL url = new URL(link);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic " + authenticationString);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept", "application/json");

//			java.io.OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(command.toString());
			osw.flush();

			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				String rtn = "";
				String line;
				while ((line = in.readLine()) != null) {
					rtn += line + "\n";
				}
				System.out.print(rtn);
			} else {
				System.out.println("HTTP Response Not OK: " + conn.getResponseCode());
			}
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			conn.disconnect();
		}
	}
	
	/**
	 * Test Class
	 * @param args CLI Arguments
	 */
	public static void main(String args[]) {
				
		rmRestScript main = new rmRestScript();
		
		main.updateLayer(1666, 1670, "HeathCareWorkerTypes");
		
//		
//		BufferedWriter writer = null;
//		try {
//			writer = new BufferedWriter(new FileWriter("update-layer-command.json"));
//			writer.write(main.updateFacilitiesJsonCommand());
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}			
		
		
	}
}
