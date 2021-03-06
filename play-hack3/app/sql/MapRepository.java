package sql;

import io.ebean.*;
import models.Map;
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
public class MapRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public MapRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    public CompletionStage<Map> insert(Map map) {
        return supplyAsync(() -> {
             ebeanServer.insert(map);
             return map;
        }, executionContext);
    }
}
