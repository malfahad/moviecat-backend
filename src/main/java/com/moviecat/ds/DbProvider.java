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

/**
 * This class connects to the MongoDB datasource.
 * We create a MongoClient which is used to initialize a Morphia Object so we can manipulate objects as MongoDB collections.
 */

public class DbProvider {

    private static final Logger log = LogManager.getLogger(DbProvider.class);

    static MongoClient mongoClient;
    static Datastore database;
    static Map<String, Object> configs = (Map<String, Object>) Configuration.config().get("db");

    static {
        open();
    }

    public static void open() {

        if (mongoClient != null) {
            mongoClient.close();
        }

        ServerAddress serverAddress = ServerAddressHelper.createServerAddress(System.getenv(configs.get("host").toString()), Integer.parseInt(System.getenv(configs.get("port").toString())));
        MongoCredential mongoCredential = MongoCredential.createCredential(System.getenv(configs.get("user").toString()), configs.get("database").toString(), System.getenv(configs.get("password").toString()).toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().build();
        mongoClient = new MongoClient(serverAddress, mongoCredential, options);

        Morphia morphia = new Morphia();
        morphia.mapPackage("com.moviecat.model");
        database = morphia.createDatastore(mongoClient, configs.get("database").toString());
        database.ensureIndexes();

        log.info("Database Initialized Successfully!");
    }

    /**
     * Get the Morphia DataStore Connection
     *
     * @return The Morphia Datastore
     */
    public static Datastore connection() {
        return database;
    }

    /**
     * Call before Application shutdown to cleanly close the database connection and prevent memory leaks
     */
    public static void close() {
        if (mongoClient == null) return;
        mongoClient.close();
        log.info("Database closed Successfully!");
    }
}