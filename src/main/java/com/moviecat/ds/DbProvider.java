package com.moviecat.ds;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.internal.connection.ServerAddressHelper;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DbProvider {

    private static final Logger log = LogManager.getLogger(DbProvider.class);

    static MongoClient mongoClient;
    static Datastore database;
    static Map<String, Object> configs = (Map<String, Object>) Configuration.config().get("db");

    static {

        ServerAddress serverAddress = ServerAddressHelper.createServerAddress(configs.get("host").toString(), Integer.parseInt(configs.get("port").toString()));
        MongoCredential mongoCredential = MongoCredential.createCredential(configs.get("user").toString(), configs.get("database").toString(), configs.get("password").toString().toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().build();
        mongoClient = new MongoClient(serverAddress, mongoCredential, options);

        Morphia morphia = new Morphia();
        morphia.mapPackage("com.moviecat.model");
        database = morphia.createDatastore(mongoClient, configs.get("database").toString());
        database.ensureIndexes();

        log.info("Database Initialized Successfully!");
    }

    public static Datastore connection() {
        return database;
    }

    public static void close() {
        if (mongoClient == null) return;
        mongoClient.close();
        log.info("Database closed Successfully!");
    }
}