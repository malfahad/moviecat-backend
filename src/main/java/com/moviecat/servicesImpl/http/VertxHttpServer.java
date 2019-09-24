package com.moviecat.servicesImpl.http;

import com.moviecat.ds.Configuration;
import com.moviecat.servicesImpl.init.InitScripts;
import com.moviecat.test.routers.VertxRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class VertxHttpServer extends AbstractVerticle {

    private static final Logger log = LogManager.getLogger(VertxHttpServer.class);
    static Map<String, Object> configs = (Map<String, Object>) Configuration.config().get("server");

    public void start(Future<Void> startFuture) {

        Router router = Router.router(vertx);
        HttpServer server = vertx.createHttpServer();

        router.route().handler(BodyHandler.create());

        router.post(configs.get("route").toString()).handler(ctx -> {
            VertxRouter.handleRoutes(ctx);
        });

        // Now bind the server:
        server.requestHandler(router).listen(Integer.parseInt(configs.get("port").toString()), res -> {
            if (res.succeeded()) {
                startFuture.complete();
                InitScripts.init();
            } else {
                startFuture.fail(res.cause());
            }
        });
    }
}