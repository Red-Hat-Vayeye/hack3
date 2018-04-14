package tasks;

import azure.*;

import javax.inject.*;

import services.*;

import sql.*;

import models.*;

import akka.actor.*;

import scala.concurrent.*;
import scala.concurrent.duration.*;


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

	@Inject
	public TwitterTask(ActorSystem actorSys,
		TwitterTaskExecutionContext executor,
		TwitterStream stream,
		GoogleMapsGeolocation geolocationApi,
		MapRepository repository) {
        this.actorSys = actorSys;
        this.executor = executor;
        this.stream = stream;
	this.geolocationApi = geolocationApi;
	this.repository = repository;

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
			keywords = TextCommon.prettify( WordClassifier.evalTweets( (ArrayList<String>) texts, 50));
		} catch(Exception e) {
			e.printStackTrace();
		}
    	final List<String> geolocations = 
				geolocationApi.geolocation( filtered.extractLocation() );

		final List<Double> lat = new ArrayList<>();
		final List<Double> lng = new ArrayList<>();
		for (String s: geolocations) {
			String[] coor = s.split(",");
			lat.add(Double.parseDouble(coor[0].trim()));
			lng.add(Double.parseDouble(coor[1].trim()));
		}

		for(int i = 0; i < lat.size(); i++) {
			Map map = new Map();
			map.latitude = lat.get(i);
			map.longitude = lng.get(i);
			map.type = Map.Type.prestamo;
			map.percentage = 0.5f;			

			repository.insert(map);	
		}
		
	}	

}
