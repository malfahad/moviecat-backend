package com.moviecat.services.http;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainVerticle {

    private static final Logger log = LogManager.getLogger(MainVerticle.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle("com.moviecat.services.http.VertxHttpServer", options, res -> {

            Runtime.getRuntime().addShutdownHook(new Thread(() -> vertx.close()));

            if (res.succeeded()) {
                log.info("Verticle deployed successfully with id : " + res.result());
            } else {
                log.error("Verticle Deployment failed!", res.cause());
            }
        });
    }

}
