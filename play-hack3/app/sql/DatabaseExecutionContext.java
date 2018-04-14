package sql;

import akka.actor.*;

import play.libs.concurrent.*
;
import scala.concurrent.duration.*;

import javax.inject.*;

import java.util.concurrent.*;

public class DatabaseExecutionContext extends CustomExecutionContext {

    @Inject
    public DatabaseExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "akka.actor.default-dispatcher");
    }
}
