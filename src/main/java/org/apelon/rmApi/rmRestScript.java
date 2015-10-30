package org.apelon.rmApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.ValueSet;
import org.hl7.fhir.ValueSetContains;
import org.omg.CORBA_2_3.portable.OutputStream;

import com.apelon.demo.dts.fhir.simpleclient.util.http.HttpMessageSender;
import com.apelon.dts.fhir.translator.valueset.xml.FhirXmlTranslator;

import org.apache.log4j.Logger;
import org.hl7.fhir.ValueSet;
import org.hl7.fhir.ValueSetContains;

import com.apelon.demo.dts.fhir.simpleclient.util.http.HttpMessageSender;
import com.apelon.dts.fhir.translator.valueset.xml.FhirXmlTranslator;

public class rmRestScript {
	
	private static final Logger logger = Logger.getLogger(rmRestScript.class);
	private StringBuffer sb;
	
	public void newJsonCommand() {
		sb = new StringBuffer();
	}
	
	public void addCommand(String c) {
		sb.append(c + System.lineSeparator());
	}
	
	public String getJsonCommand() {
		return this.sb.toString();
	}
	
	/**
	 * The JSON Command to update facilities layer for Resource MAP, from FHIR data
	 * TODO: Add Getters and Setters for Layer ID's
	 * @return Resource Map Update Layer JSON Command (String)
	 */
	public String updateFacilitiesJsonCommand() {
		this.addCommand("{");
			this.addCommand("\"layer\":{");
				this.addCommand("\"id\":\"1670\",");
				this.addCommand("\"name\":\"Medical Facility Information\",");
				this.addCommand("\"ord\":\"1\",");
				this.addCommand("\"fields_attributes\":{");
					this.addCommand("\"0\":{ ");
						this.addCommand("\"id\":\"13377\",");
						this.addCommand("\"name\":\"Facility Type\",");
						this.addCommand("\"code\":\"facility_type\",");
						this.addCommand("\"kind\":\"select_one\",");
						this.addCommand("\"ord\":\"3\",");
						this.addCommand("\"layer_id\":\"1670 Type\",");
						this.addCommand("\"config\":{");
							this.addCommand("\"options\":{");
								int count = 0;
								List<ValueSetContains> facilitiesData = this.getFhirFacilities();
								for(ValueSetContains row : facilitiesData) {
									this.addCommand("\"" + count + "\":{ ");
										this.addCommand("\"id\":\"" + (count + 1) + "\",");
										this.addCommand("\"code\":\"" + row.getCode().getValue() + "\",");
										this.addCommand("\"kind\":\"" + row.getDisplay().getValue() + "\"");
									this.addCommand("}");
									if(count != facilitiesData.size()) {
										this.addCommand(","); 
									}
								}
							this.addCommand("}");
						this.addCommand("}");
					this.addCommand("}");
				this.addCommand("}");
			this.addCommand("}");
		this.addCommand("}");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Gets FHIR Facilities
	 * TODO: Break out the facilities value-set to make this more generic for more JSON commands
	 * @return List of Facilities (from FHIR)
	 */
	public List<ValueSetContains> getFhirFacilities() {
		try {
			
			// get parameters
			logger.debug("Getting value set from the demo server");
			String uid = "dtsadminuser";
			String pwd = "dtsadmin";
			String url = "http://fhir.ext.apelon.com:8081/dtsserverws/fhir";
			url += "/ValueSet/HeathCareWorkerTypes/$expand";
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
	public void executeCommand(String command) {
		HttpURLConnection conn = null;
		try {
			String base = "http://resourcemap.instedd.org";
			//String link = base + "/api/collections/1666/layers/1673.json";
			String link = base + "/api/collections/1666/layers/1673.json";
			String uid = "vkaloidis@apelon.com";
			String pwd = "apelon123";

			// get the authentication string
			String authenticationString = new String(Base64.encodeBase64((uid
					+ ":" + pwd).getBytes()));
			// get a connection
			URL url = new URL(link);
			conn = (HttpURLConnection) url.openConnection();
			// set the parameters
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic "
					+ authenticationString);
			conn.setRequestProperty("Content-Type", "application/json");

			java.io.OutputStream os = conn.getOutputStream();
			os.write(command.getBytes());
			os.flush();

			// make the connection, send the request, and get the response
			InputStream content = (InputStream) conn.getInputStream();
			// read the response
			BufferedReader in = new BufferedReader(new InputStreamReader(
					content));
			String rtn = "";
			String line;
			while ((line = in.readLine()) != null) {
				rtn += line + "\n";
			}

			// return the response
			System.out.print(rtn);

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
		main.executeCommand(main.getJsonCommand());
	}
}
