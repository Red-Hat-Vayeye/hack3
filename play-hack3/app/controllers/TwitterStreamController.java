package controllers;

import azure.*;

import services.*;

import play.mvc.*;
import play.libs.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

import javax.inject.*;

import com.fasterxml.jackson.databind.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class TwitterStreamController extends Controller {

	private final TwitterStream twitterStream;
	private final GoogleMapsGeolocation geolocationApi;

	@Inject
	public TwitterStreamController(TwitterStream twitterStream, GoogleMapsGeolocation geolocationApi) {
		this.twitterStream = twitterStream;
		this.geolocationApi = geolocationApi;
	}

   	public Result index() {
		final List<String> twits = twitterStream.filteredTwits(50).get();

		String msg = "";

		if(twits.size() > 0) {
			for (String twit : twits) {
				msg += twit + "\n";
			}
		} else {
			msg = "Error";
		}

		return ok(msg);
    }

    public Result keywords() {
    	final List<String> texts = twitterStream.filteredTwits(10).extractText();
	
	String msg = "Error";
	try {
    		msg = TextCommon.prettify(WordClassifier.evalTweets((ArrayList<String>) texts, 10));
	} catch(Exception e) {
		e.printStackTrace();
	}
		return ok(msg);
    }

    public Result geolocation() {
		final List<String> geolocations = 
				geolocationApi.geolocation( twitterStream.filteredTwits(10).extractLocation() );

		String msg = "";

		if(geolocations.size() > 0) {
			for (String geolocation : geolocations) {
				msg += geolocation + "\n";
			}
		} else {
			msg = "Error";
		}

		return ok(msg);
    }

}
