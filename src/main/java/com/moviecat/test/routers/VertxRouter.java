/*
 *  Licensed to the MovieCat, Inc. under one
 *  or more contributor license agreements. See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.
 *
 *  You may not use this file except in compliance
 *  with the License.
 *
 */

package com.moviecat.test.routers;

import com.moviecat.ds.Configuration;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertxRouter {

    private static final Logger log = LogManager.getLogger(VertxRouter.class);
    static RPCRouter rpcRouter = new RPCRouter();

    public static void handleRoutes(RoutingContext ctx) {
        log.info("ctx.getBodyAsJson().encode(): -----> " + ctx.getBodyAsJson().encode());
        String resp = Configuration.server().handle(ctx.getBodyAsJson().encode(), rpcRouter);

        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "application/json");
        response.end(resp);
    }
}
