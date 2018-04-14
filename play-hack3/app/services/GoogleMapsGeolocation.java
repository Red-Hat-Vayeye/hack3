package services;

import java.util.*;

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
    				location.textValue()
    			).await();
				if(results.length > 0) {
					JsonNode json = Json.parse(twit);
					JsonNode location = json.findValue("location");
					coordinates.add(location.textValue());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return coordinates;
    }

}