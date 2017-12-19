package com.p.lodz.pl.bookexchange.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class GetLocation {

	public static Location getLocation() {
		String json = readUrl("http://freegeoip.net/json/" + getMyIp());
		Gson gson = new Gson();
		Location loc = gson.fromJson(json, Location.class);
		return loc;
	}

	private static String readUrl(String url) {
		try {
			URL website = new URL(url);
			URLConnection connection = website.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);

			in.close();

			return response.toString();
		} catch (Exception e) {
			System.out.println("Something went wrong when getting location");
		}
		return "";
	}

	private static String getMyIp() {
		String ip = "";
		URL whatismyip;
		try {
			whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine(); // you get the IP as a String
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ip;
	}

	public static double distFromInMeters(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);
		return dist;
	}
}
