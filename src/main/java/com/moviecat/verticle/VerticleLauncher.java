package com.moviecat.verticle;

import com.moviecat.ds.DbProvider;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class VerticleLauncher {

    private static final Logger log = LogManager.getLogger(VerticleLauncher.class);

    public static void main(String[] args) {
        VertxOptions option = new VertxOptions();
        option.setBlockedThreadCheckInterval(60);
        option.setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS);
        option.setEventLoopPoolSize(10);
        option.setWorkerPoolSize(10);

        Vertx vertx = Vertx.vertx(option);

        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(MainVerticle::new, options, res -> {

            Runtime.getRuntime().addShutdownHook(
                    new Thread(
                            () -> {
                                DbProvider.close();
                                vertx.close();
                            }
                    ));

            if (res.succeeded()) {
                log.info("Verticle deployed successfully with id : " + res.result());
            } else {
                log.error("Verticle Deployment failed!", res.cause());
            }
        });
    }

}
