package com.carrotass.jira.query;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class QueryHelper {
	
	public static String formatStringToURL(String source) throws Exception {
		return URLEncoder.encode(source, "UTF-8");
	}
	
	public static URLConnection PrepareConnection(String request, List<String> cookies) throws Exception {
		URL url = new URL(request);
		URLConnection connection = url.openConnection();
		
		if (cookies != null) {
			StringBuilder cookiesString = new StringBuilder();
			for (int i=0;i<cookies.size(); i++) {
				if (i>0) {
					cookiesString.append(";");
				}
				cookiesString.append(cookies.get(i));
			}
			System.out.println("Cookies: " + cookiesString.toString());
			connection.setRequestProperty("Cookie", cookiesString.toString());
		}
		
		return connection;
	}
}
