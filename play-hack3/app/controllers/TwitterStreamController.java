package controllers;

import play.mvc.*;
import play.libs.*;

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

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class TwitterStreamController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
	/** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
	BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
	BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

	/** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
	Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
	StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
	// Optional: set up some followings and track terms
	List<Long> followings = new ArrayList<>();
	followings.add(1234L);
	followings.add(566788L);
	List<String> terms = new ArrayList<>();
	terms.add("dinero");
	terms.add("prestamo");
	terms.add("nuevo");
	List<String> languages = new ArrayList<>();
	languages.add("es-419");
	//hosebirdEndpoint.followings(followings);
	hosebirdEndpoint.trackTerms(terms);
	//hosebirdEndpoint.language(languages);

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
	int max = 50;
	List<String> msgs = new ArrayList<>();
	String msg = "";
	while (!hosebirdClient.isDone() && i < max) {
  		try {
			String txt = msgQueue.take();
			JsonNode json = Json.parse(txt);
			msgs.add(json.findPath("text").textValue());
			msg += txt + "\n";
			i++;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	hosebirdClient.stop();

	/*
	
		INSERT CODE HERE

	*/

	return ok(msg);
    }

}
