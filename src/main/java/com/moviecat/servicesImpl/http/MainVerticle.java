package com.moviecat.servicesImpl.http;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class MainVerticle {

    private static final Logger log = LogManager.getLogger(MainVerticle.class);

    public static void main(String[] args) {
        VertxOptions option = new VertxOptions();
        option.setBlockedThreadCheckInterval(60);
        option.setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS);
        option.setEventLoopPoolSize(10);
        option.setWorkerPoolSize(10);

        Vertx vertx = Vertx.vertx(option);

        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(VertxHttpServer::new, options, res -> {

            Runtime.getRuntime().addShutdownHook(new Thread(() -> vertx.close()));

            if (res.succeeded()) {
                log.info("Verticle deployed successfully with id : " + res.result());
            } else {
                log.error("Verticle Deployment failed!", res.cause());
            }
        });
    }

}
