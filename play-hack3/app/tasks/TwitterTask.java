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

	@Inject
	public TwitterTask(ActorSystem actorSys, TwitterTaskExecutionContext executor) {
        	this.actorSys = actorSys;
        	this.executor = executor;

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
		System.out.println("\nRunning twitter task\n");
	}	

}
