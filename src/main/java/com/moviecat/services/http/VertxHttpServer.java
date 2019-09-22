package com.moviecat.services.http;

import com.moviecat.services.init.InitScripts;
import com.moviecat.services.routers.VertxRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertxHttpServer extends AbstractVerticle {

    private static final Logger log = LogManager.getLogger(VertxHttpServer.class);

    private Vertx vertx = Vertx.vertx();


    public void start(Future<Void> startFuture) {

        Router router = Router.router(vertx);
        HttpServer server = vertx.createHttpServer();

        router.route().handler(BodyHandler.create());

        router.post("/v1/moviecat").handler(ctx -> {
            VertxRouter.handleRoutes(ctx);
        });

        // Now bind the server:
        server.requestHandler(router).listen(9191, res -> {
            if (res.succeeded()) {
                startFuture.complete();
                InitScripts.init();
            } else {
                startFuture.fail(res.cause());
            }
        });
    }
}