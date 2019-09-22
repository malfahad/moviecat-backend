package com.moviecat.services.routers;

import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcMethod;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcService;
import com.moviecat.model.Country;
import com.moviecat.services.implementations.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@JsonRpcService
public class RPCRouter {

    private static final Logger log = LogManager.getLogger(RPCRouter.class);

    @JsonRpcMethod
    public boolean isAlive() {
        return BaseService.isAlive();
    }

    @JsonRpcMethod
    public List<Country> fetchCountries() {
        return BaseService.fetch(Country.class);
    }
}