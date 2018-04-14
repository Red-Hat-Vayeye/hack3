/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package sql;

import play.mvc.*;
import play.db.*;

import java.util.concurrent.*;
import java.sql.*;

import javax.inject.*;

@Singleton
class JavaApplicationDatabase {
/*
    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public JavaApplicationDatabase(Database db, DatabaseExecutionContext context) {
        this.db = db;
        this.executionContext = executionContext;
    }

   public CompletionStage<Integer> updateSomething() {
       return CompletableFuture.supplyAsync(() -> {
           return db.withConnection(connection -> {
               // do whatever you need with the db connection
               return 1;
           });
       }, executionContext);
   }*/
}
