package services;

import play.mvc.*;
import play.libs.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

import com.twitter.hbc.core.*;
import com.twitter.hbc.core.event.*;
import com.twitter.hbc.core.endpoint.*;
import com.twitter.hbc.core.processor.*;
import com.twitter.hbc.httpclient.auth.*;
import com.twitter.hbc.*;

import com.fasterxml.jackson.databind.*;

import com.google.maps.*;
import com.google.maps.model.*;
import com.google.gson.*;


public class GoogleMapsGeolocation {
	

	public List<String> geolocation(List<String> locations) {

    	String gKey = "AIzaSyBwkU3z6cZlSGW4PNa1L1SlQ6TlcDpClFM";

    	GeoApiContext context = new GeoApiContext.Builder()
    	.apiKey(gKey)
    	.build();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		GeocodingResult[] results = null;

		final List<String> coordinates = new ArrayList<>();

		for(String location : locations) {
			try {
				results = GeocodingApi.geocode(
					context,
    				location
    			).await();
				if(results.length > 0) {
					String result = gson.toJson(results); 
					JsonNode json = Json.parse(result);
					JsonNode coor = json.findValue("location");
					if (result == null || coor == null() ) {
						coordinates.add(result);
					} else {
						coordinates.add(coor.textValue());
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return coordinates;
    }

}
