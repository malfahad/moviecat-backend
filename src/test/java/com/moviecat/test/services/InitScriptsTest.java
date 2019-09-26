package com.moviecat.test.services;

import com.moviecat.ds.DbProvider;
import com.moviecat.model.Country;
import com.moviecat.servicesImpl.init.InitScripts;
import dev.morphia.Datastore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InitScriptsTest {

    @Test
    void performInit() {
        Datastore ds = DbProvider.connection();
        //Boolean repopulateAfterTest = ds.createQuery(Country.class).count() > 0;

        InitScripts.init();
        Assertions.assertTrue(ds.createQuery(Country.class).count() > 0, "InitScript failed to run initializing logic");

        //TODO: Collection dropping not required. Next 2 lines will soon be unnecessary. Leaving here for testing purposes
        //ds.getDatabase().getCollection("Country").drop();
        //Assertions.assertTrue(ds.createQuery(Country.class).count() == 0, "Failed to delete country collection");

        //Returning the database to its original state
        //if (repopulateAfterTest) {
        //    InitScripts.init();
        //    Assertions.assertTrue(ds.createQuery(Country.class).count() > 0, "Failed to return database records to original results");
        //}
    }
}
