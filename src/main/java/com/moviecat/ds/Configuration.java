package com.moviecat.ds;

import com.github.arteam.simplejsonrpc.server.JsonRpcServer;
import com.google.common.cache.CacheBuilderSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to read all external configurations to be referenced by all classes in the app.
 */

public class Configuration {

    private static final Logger log = LogManager.getLogger(Configuration.class);

    static Map<String, Object> configuration = new HashMap<>();
    static JsonRpcServer jsonRpcServer;

    static {
        jsonRpcServer = JsonRpcServer.withCacheSpec(CacheBuilderSpec.disableCaching());
        try {
            Yaml yaml = new Yaml();
            configuration = (Map<String, Object>) yaml.load(new FileReader(new File("dbConfig.yml")));
            log.info("Configurations Loaded Successfully!");
        } catch (Exception ex) {
            log.error("YAML loading error! : ", ex);
        }
    }

    public static Map<String, Object> config() {
        return configuration;
    }

    public static JsonRpcServer server() {
        return jsonRpcServer;
    }
}