package controllers;

import azure.*;

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

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class TwitterStreamController extends Controller {

	private final static String PATH = "/home/hack/hack3/play-hack3/app/controllers/";

    public Result index() {
		final List<String> twits = getCurrentTwits(50);

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
    	final List<String> texts = getCurrentTwitsText(10);

    	String msg = "";
		if(twits.size() > 0) {
			for (String text : texts) {
				msg += text + "\n";
			}
		} else {
			msg = "Error";
		}

		return ok(msg);
    }

    public Result geolocation() {
		final List<String> twits = getCurrentTwits(50);

    	String gKey = "AIzaSyBwkU3z6cZlSGW4PNa1L1SlQ6TlcDpClFM";

    	GeoApiContext context = new GeoApiContext.Builder()
    	.apiKey(gKey)
    	.build();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		GeocodingResult[] results = null;

		int i = 0;
		String twit;
		boolean found = false;
		while(i < twits.size() && !found) {
			twit = twits.get(0);
			JsonNode json = Json.parse(twit);
			JsonNode location = json.findValue("location");
			if(location != null
				&& location.textValue() != null
				&& location.textValue().trim().length() > 0) {
				results = GeocodingApi.geocode(
					context,
	    			location.textValue()
	    		).await();
				if(results.size > 0)
					found = true;
			}
			i++;
		}

		if(found && results != null) {
			return ok(gson.toJson(results));
		} else {
			return ok("Error");
		}
    }

    private List<String> getCurrentTwitsText(int amount) {
    	final List<String> twits = getCurrentTwits(amount);
    	final List<String> texts = new ArrayList<>();

    	for(String twit : twits) {
    		JsonNode json = Json.parse(twit);
			texts.add(json.findPath("text").textValue());
    	}
    	return texts;
    }

    private List<String> getCurrentTwits(int amount) {
    	/** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
		BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
		BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

		/** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
		Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
		StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

		final List<String> terms = new ArrayList<>();
		try {
			File file = new File(PATH + "TwitterSearchKeywords.txt");
			if(!file.exists())
				System.err.println("File doesnt exist");		

			if (!file.canRead())
				System.err.println("Cant read file");
			
			BufferedReader twKeyReader=new BufferedReader(new FileReader(file));
			String term = "";
			while((term = twKeyReader.readLine()) != null){
				terms.add(term);
			}
			twKeyReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		hosebirdEndpoint.trackTerms(terms);

		// These secrets should be read from a config file
		String key = "fFyulrm59tX5ZtJaBYrw5TXLK";
		String secret = "3hPC38y7VnMslPN54MHlm6I7D1aukPOo4WMkBFXW6Oi1gpdoeP";
		String token = "3065919229-4Cjy0NVkKLoVZU6edAMBbPa49GTRhNYY6QHTZww";
		String tSecret = "EuikOE7MNbtx9ZfTvQgpbfu2dAmlRWVZzaFZPrYW14UDQ";
		
		Authentication hosebirdAuth = new OAuth1(key, secret, token, tSecret);

		ClientBuilder builder = new ClientBuilder()
	  		.name("Hosebird-Client-01")                              // optional: mainly for the logs
	  		.hosts(hosebirdHosts)
	  		.authentication(hosebirdAuth)
	  		.endpoint(hosebirdEndpoint)
	  		.processor(new StringDelimitedProcessor(msgQueue))
	  		.eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

		Client hosebirdClient = builder.build();
		// Attempts to establish a connection.
		hosebirdClient.connect();
	 
		int i = 0;
		int j = 0;

		int MAX = 100;
		final List<String> twits = new ArrayList<>();
		while (!hosebirdClient.isDone() && i < amount && j < MAX) {
	  		try {
				String txt = msgQueue.take();
				twits.add(txt);
				i++;
			} catch(Exception e) {
				e.printStackTrace();
			}
			j++;
		}
		hosebirdClient.stop();

		return twits;
    }

}
