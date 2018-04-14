package sql;

import io.ebean.*;
import models.*;
import play.db.ebean.EbeanConfig;

import javax.inject.*;
import javax.persistence.*;

import java.util.Optional;
import java.util.concurrent.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * A repository that executes database operations in a different
 * execution context.
 */
public class TwitRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public TwitRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    public CompletionStage<Twit> insert(Twit twit) {
        return supplyAsync(() -> {
             ebeanServer.insert(twit);
             return twit;
        }, executionContext);
    }
}
