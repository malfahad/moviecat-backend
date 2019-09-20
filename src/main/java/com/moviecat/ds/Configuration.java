package com.moviecat.ds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private static final Logger log = LogManager.getLogger(Configuration.class);

    static Map<String, Object> configuration = new HashMap<>();

    static {
        try {
            Yaml yaml = new Yaml();
            configuration = (Map<String, Object>) yaml.load(Configuration.class.getClassLoader().getResourceAsStream("dbConfig.yml"));
            log.info("Configurations Loaded Successfully!");
        } catch (Exception ex) {
            log.error("YAML loading error! : ", ex);
        }
    }

    public static Map<String, Object> config() {
        return configuration;
    }
}