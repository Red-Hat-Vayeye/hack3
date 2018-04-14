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

public class TwitterStream {

	private final static String PATH = "/home/hack/hack3/play-hack3/app/services/";
	private final static int MAX = 200;

	private final List<String> terms;
	private List<String> twits;
	private BlockingQueue<String> msgQueue;
	private BlockingQueue<Event> eventQueue;

	public TwitterStream() {
		terms = new ArrayList<>();
		initTerms();
		twits = new ArrayList<>();
	}

	public TwitterStream(List<String> twits) {
		terms = new ArrayList<>();
		initTerms();
		this.twits = twits;
	}

	private void initTerms() {
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
	}

	public List<String> get() {
		return twits;
	}

	public List<String> extractLocation() {
		final List<String> locations = new ArrayList<>();

    	for( String twit : twits ) {
			JsonNode json = Json.parse(twit);
    		JsonNode location = json.findValue("location");
			if(location != null
				&& location.textValue() != null
				&& location.textValue().trim().length() > 0) {
					locations.add(location.textValue());
			}
    	}

    	return locations;
    }

	public List<String> extractLocation(List<String> twits) {
    	List<String> locations = new ArrayList<>();

    	for( String twit : twits ) {
			JsonNode json = Json.parse(twit);
    		JsonNode location = json.findValue("location");
			if(location != null
				&& location.textValue() != null
				&& location.textValue().trim().length() > 0) {
					locations.add(location.textValue());
			}
    	}

    	return locations;
    }

    public List<String> extractText(List<String> twits) {
    	List<String> texts = new ArrayList<>();

    	for( String twit : twits ) {
			JsonNode json = Json.parse(twit);
    		texts.add(json.findValue("text").textValue());
    	}

    	return texts;
    }

    public List<String> extractText() {
    	List<String> texts = new ArrayList<>();

    	for( String twit : twits ) {
			JsonNode json = Json.parse(twit);
    		texts.add(json.findValue("text").textValue());
    	}

    	return texts;
    }


    public TwitterStream filteredTwits(int amount) {
    	Client hosebirdClient = buildClient();

		hosebirdClient.connect();
	 
		int i = 0;
		int j = 0;
		
		while (!hosebirdClient.isDone() && i < amount && j < MAX) {
	  		try {
				String twit = msgQueue.take();
				JsonNode json = Json.parse(twit);
    			JsonNode coordinates = json.findValue("coordinates");
				JsonNode location = json.findValue("location");
				boolean add = coordinates != null
					&& location.textValue() != null
					&& !location.textValue().isEmpty();
				add = add || (location != null
					&& location.textValue() != null
					&& !location.textValue().isEmpty());
				add = add && terms.parallelStream().anyMatch(twit::contains);

				if(add) {
					twits.add(twit);
					i++;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			j++;
		}
		hosebirdClient.stop();


    	return this;
    }

	public TwitterStream filterTwits() {
		final List<String> filteredTwits = new ArrayList<>();

		for(String twit : twits) {
	  		try {
				JsonNode json = Json.parse(twit);
    			JsonNode coordinates = json.findValue("coordinates");
				JsonNode location = json.findValue("location");
				boolean add = coordinates != null
					&& location.textValue() != null
					&& !location.textValue().isEmpty();
				add = add || (location != null
					&& location.textValue() != null
					&& !location.textValue().isEmpty());
				add = add && terms.parallelStream().anyMatch(twit::contains);

				if(add) {
					filteredTwits.add(twit);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		twits = filteredTwits;

    	return this;
    }

    public TwitterStream getCurrentTwits(int amount) {
		Client hosebirdClient = buildClient();

		hosebirdClient.connect();
	 
		int i = 0;
		int j = 0;

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

		return this;
    }

    private Client buildClient() {

    	/** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
		msgQueue = new LinkedBlockingQueue<String>(100000);
		eventQueue = new LinkedBlockingQueue<Event>(1000);

		/** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
		Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
		StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

		if(terms.size() > 0)
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

		return builder.build();
    }

}
