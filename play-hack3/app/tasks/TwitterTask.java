package tasks;

import azure.*;

import javax.inject.*;

import services.*;

import sql.*;

import models.*;

import akka.actor.*;

import scala.concurrent.*;
import scala.concurrent.duration.*;

import com.google.gson.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.lang.*;

public class TwitterTask implements Runnable {

	private final ActorSystem actorSys;
	private final TwitterTaskExecutionContext executor;
	private final TwitterStream stream;
	private final GoogleMapsGeolocation geolocationApi;
	private final MapRepository repository;
	private final TwitRepository repo;

	@Inject
	public TwitterTask(ActorSystem actorSys,
		TwitterTaskExecutionContext executor,
		TwitterStream stream,
		GoogleMapsGeolocation geolocationApi,
		MapRepository repository,
		TwitRepository repo) {
        this.actorSys = actorSys;
        this.executor = executor;
        this.stream = stream;
	this.geolocationApi = geolocationApi;
	this.repository = repository;
	this.repo = repo;

        this.initialize();
    }

	private void initialize() {
		this.actorSys.scheduler().schedule(
			Duration.create(10, TimeUnit.SECONDS), // initialDelay
            Duration.create(5, TimeUnit.MINUTES), // interval
            this,
            this.executor // using the custom executor
        );
    }
	
	@Override
	public void run() {
		final TwitterStream filtered = stream.filteredTwits(50);

		final List<String> texts = filtered.extractText();

		String keywords = "";
		try {
			keywords = TextCommon.prettify( WordClassifier.evalTweets( (ArrayList<String>) texts, 25));
		} catch(Exception e) {
			e.printStackTrace();
		}
    	final List<String> geolocations = 
				geolocationApi.geolocation( filtered.extractLocation() );

		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(keywords).getAsJsonObject();
		JsonArray array = root.getAsJsonArray("documents");
		int j = 0;		
		for (JsonElement e : array ) {
			JsonObject o = e.getAsJsonObject();
			JsonArray words = o.getAsJsonArray("keyPhrases");
			String keyPhrases = "";
			for (JsonElement w : words ) {
				keyPhrases += w.getAsString() + ", ";
			}
			Twit twit = new Twit();
			twit.text = texts.get(j++);
			twit.keywords = keyPhrases;
			repo.insert(twit);
		}

		final List<Double> lat = new ArrayList<>();
		final List<Double> lng = new ArrayList<>();
		for (String s: geolocations) {
			String[] coor = s.split(",");
			lat.add(Double.parseDouble(coor[0].trim()));
			lng.add(Double.parseDouble(coor[1].trim()));
		}

		ThreadLocalRandom random = ThreadLocalRandom.current();
		for(int i = 0; i < lat.size(); i++) {
			Map map = new Map();
			map.latitude = lat.get(i);
			map.longitude = lng.get(i);
			map.type = random.nextBoolean() ? Map.Type.credito : Map.Type.seguro;
			map.percentage = random.nextFloat();			

			repository.insert(map);	
		}
		
	}	

}
