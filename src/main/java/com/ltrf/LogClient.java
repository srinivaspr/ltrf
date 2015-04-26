package com.ltrf;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class LogClient {
	
	private static String uri;
	
	public static void trackMessage(LogMessage message) {
		try{
			JSONObject request = new JSONObject();
			request.put("appName", message.getAppName());
			request.put("key", message.getKey());
			request.put("message", message.getMessage());
			request.put("additionalDetails", message.getAdditionalDetails());
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(request.toString(),headers);
			restTemplate.exchange(uri+"/saveLogMessage", HttpMethod.POST, entity, String.class);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void setURI(String url)
	{
		uri = url;
	}

}
