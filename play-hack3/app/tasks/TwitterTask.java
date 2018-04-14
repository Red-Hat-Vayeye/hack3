package tasks;

import javax.inject.*;

import akka.actor.*;

import scala.concurrent.*;
import scala.concurrent.duration.*;

import java.util.concurrent.*;
import java.lang.*;

public class TwitterTask implements Runnable {

	private final ActorSystem actorSys;
	private final TwitterTaskExecutionContext executor;
	private final TwitterStream stream;

	@Inject
	public TwitterTask(ActorSystem actorSys,
		TwitterTaskExecutionContext executor
		TwitterStream stream) {
        this.actorSys = actorSys;
        this.executor = executor;
        this.stream = stream;

        this.initialize();
    }

	private void initialize() {
		this.actorSys.scheduler().schedule(
			Duration.create(10, TimeUnit.SECONDS), // initialDelay
            Duration.create(10, TimeUnit.MINUTES), // interval
            this,
            this.executor // using the custom executor
        );
    }
	
	@Override
	public void run() {
		final TwitterStream filtered = twitterStream.filteredTwits(2);

		final List<String> texts = filtered.extractText();

    	String keywords = TextCommon.prettify(WordClassifier.evalTweets(texts, 10));

    	final List<String> geolocations = 
				geolocationApi.geolocation( twitterStream.filteredTwits(10).extractLocation() );

		System.out.printl("\nTweets:\n\t")
		System.out.println(texts.get(0) +  "\n\t"+ texts.get(1) );
		System.out.print("\nLocations:\n\t");
		System.out.println(geolocations.get(0) +  "\n\t"+ geolocations.get(1) );
		System.out.print("\neywords:\n\t");
		System.out.println(keywords);
	}	

}
