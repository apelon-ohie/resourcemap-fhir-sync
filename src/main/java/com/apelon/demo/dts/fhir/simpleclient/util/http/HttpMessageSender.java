package com.apelon.demo.dts.fhir.simpleclient.util.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class HttpMessageSender {

	//
	// instance variables
	//

	private String uid;

	private String pwd;

	//
	// constructor
	//

	public HttpMessageSender(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
	}

	public String get() {
		return get(null);
	}

	public String get(String url) {
		try {
			// get the authentication string
			String authenticationString = new String(Base64.encodeBase64((this.uid + ":" + this.pwd).getBytes()));
			// get a connection
			URL urlObj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			// set the parameters
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic " + authenticationString);
			// make the connection, send the request, and get the response
			conn.connect();
			InputStream content = (InputStream) conn.getInputStream();
			// read the response
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String rtn = "";
			String line;
			while ((line = in.readLine()) != null) {
				rtn += line + "\n";
			}
			// return the response
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public String post(String url, String request) {
		try {
			// get the authentication string
			String authenticationString = new String(Base64.encodeBase64((this.uid + ":" + this.pwd).getBytes()));
			// get a connection
			URL urlObj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			// set the parameters
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic " + authenticationString);
			addMessage(conn, request);
			// make the connection, send the request, and get the response
			conn.connect();
			InputStream content = (InputStream) conn.getInputStream();
			// read the response
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String rtn = "";
			String line;
			while ((line = in.readLine()) != null) {
				rtn += line;
			}
			// return the response
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public String put(String url, String request) {
		try {
			// get the authentication string
			String authenticationString = new String(Base64.encodeBase64((this.uid + ":" + this.pwd).getBytes()));
			// get a connection
			URL urlObj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			// set the parameters
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic " + authenticationString);
			addMessage(conn, request);
			// make the connection, send the request, and get the response
			conn.connect();
			InputStream content = (InputStream) conn.getInputStream();
			// read the response
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String rtn = "";
			String line;
			while ((line = in.readLine()) != null) {
				rtn += line;
			}
			// return the response
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	//
	// private method to write the message
	//
	
	private void addMessage(HttpURLConnection conn, String message) {
		try {
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(message);
			writer.flush();
			writer.close();
			os.close();
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
