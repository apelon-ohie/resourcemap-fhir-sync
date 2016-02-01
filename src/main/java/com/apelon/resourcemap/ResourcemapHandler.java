package com.apelon.resourcemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import com.apelon.config.Config;
import com.apelon.resourcemap.objects.ResourcemapField;

public class ResourcemapHandler {
	
	private ResourcemapCommandBuilders rmcb;
	
	public StringBuffer sb = new StringBuffer();
	
	
	public ResourcemapHandler() {
//		 
	}

	public ResourcemapHandler(StringBuffer sb) {
		super();
		this.sb = sb;
	}

	public void createFhirLocations(String collection_id, String valueset) {
		
		String endpoint = Config.getFhirServer() + "/api/applications/" + collection_id + "/sites.json";
		
	}
	
	public void updateFacilityLayerType(ResourcemapField fl) {
		String command = rmcb.buildUpdateLayerPayload(fl);
		
		String base = Config.getRmServer();
		String link = base + "/api/collections/" + fl.getCollectionId() + "/layers/" + fl.getLayerId() + ".json";

		this.executePut(link, command);
	}
	
	private void executePost(String endpoint, String command) {
		HttpURLConnection conn = null;
		try {
			System.out.println("Link: " + endpoint);
			String uid = Config.getRmUser() ;
			String pwd = Config.getRmPassword();

			String authenticationString = new String(Base64.encodeBase64((uid
					+ ":" + pwd).getBytes()));
			
			URL url = new URL(endpoint);
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic " + authenticationString);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept", "application/json");

//			java.io.OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(command.toString());
			osw.flush();

			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				System.out.println("COMMAND SENT OK: ");
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
	
	private void executePut(String endpoint, String command) {
		HttpURLConnection conn = null;
		try {
			System.out.println("Link: " + endpoint);
			String uid = Config.getRmUser() ;
			String pwd = Config.getRmPassword();

			String authenticationString = new String(Base64.encodeBase64((uid
					+ ":" + pwd).getBytes()));
			
			URL url = new URL(endpoint);
			
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
				System.out.println("COMMAND SENT OK: ");
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
	
	
	
}
