package com.moviecat.test.services;

import com.moviecat.test.BaseTest;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class VertxTest extends BaseTest {

    @Test
    void requestResponseTest(Vertx vertx, VertxTestContext testContext) throws Throwable {

        Checkpoint serverStarted = testContext.checkpoint();
        Checkpoint requestsServed = testContext.checkpoint(10);
        Checkpoint responsesReceived = testContext.checkpoint(10);

        vertx.createHttpServer()
                .requestHandler(req -> {
                    req.response().end("Ok");
                    requestsServed.flag();
                })
                .listen(9292, ar -> {
                    if (ar.failed()) {
                        testContext.failNow(ar.cause());
                    } else {
                        serverStarted.flag();
                    }
                });

        WebClient client = WebClient.create(vertx);
        for (int i = 0; i < 10; i++) {
            client.get(9292, "localhost", "/")
                    .as(BodyCodec.string())
                    .send(ar -> {
                        if (ar.failed()) {
                            testContext.failNow(ar.cause());
                        } else {
                            testContext.verify(() -> Assertions.assertTrue(ar.result().body().equalsIgnoreCase("Ok")));
                            responsesReceived.flag();
                        }
                    });
        }
    }
}