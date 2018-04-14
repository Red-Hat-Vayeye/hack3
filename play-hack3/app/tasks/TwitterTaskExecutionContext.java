package tasks;

import akka.actor.*;

import play.libs.concurrent.*;

import scala.concurrent.duration.*;

import javax.inject.*;

import java.util.concurrent.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class TwitterTaskExecutionContext extends CustomExecutionContext {

    @Inject
    public TwitterTaskExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "akka.actor.twitter-dispatcher");
    }
}
