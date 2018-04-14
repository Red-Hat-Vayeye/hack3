package controllers;

import play.mvc.*;

import com.google.maps.*;
import com.google.maps.model.*;

import com.google.gson.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class GeolocationController extends Controller {

	private String key = "AIzaSyBwkU3z6cZlSGW4PNa1L1SlQ6TlcDpClFM";

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

    	GeoApiContext context = new GeoApiContext.Builder()
    	.apiKey(key)
    	.build();
		try {
			GeocodingResult[] results =  GeocodingApi.geocode(
				context,
	    		"Palo Alto, California"
	    	).await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        return ok(gson.toJson(results));
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return ok("Error");
    }

}
