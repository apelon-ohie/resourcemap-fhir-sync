package com.apelon.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.apelon.config.Config;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class HttpMessageSender {

	private String uid;

	private String pwd;

	public static boolean createResourcemapSite(String endpoint, String params) {
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			System.out.println("Link: " + endpoint);
			String uid = Config.getRmUser() ;
			String pwd = Config.getRmPassword();

			String authenticationString = new String(Base64.encodeBase64((uid
					+ ":" + pwd).getBytes()));

			URL url = new URL(endpoint);

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(endpoint);

			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			nvps.add(new BasicNameValuePair("site", params));

			post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			post.addHeader("Authorization", "Basic " + authenticationString);
//			post.addHeader("Content-Type", "application/json; charset=UTF-8");
			post.addHeader("Content-Type", "application/x-www-form-urlencoded");
//			post.addHeader("Accept", "application/json");
			System.out.println("Params: " + params.toString());

			HttpResponse response = client.execute(post);
			System.out.println("Response Code : "
					+ response.getStatusLine().getStatusCode());
			System.out.println("Response : "
					+ response.getStatusLine().toString());

			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
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
		}
		return false;
	}

	public static boolean executePut(String endpoint, String command) {
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
				return true;
			} else {
				System.out.println("HTTP Response Not OK: " + conn.getResponseCode());
				return false;
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
		return false;
	}


	public static String get(String url) {
		try {
			// get the authentication string
			String authenticationString = new String(Base64.encodeBase64((Config.getFhirUser() + ":" + Config.getFhirPassword()).getBytes()));
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
