package com.moviecat.test.ds;

import com.moviecat.ds.DbProvider;
import com.moviecat.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataSourceTest extends BaseTest {

    @Test
    void testMongoConnection() {
        Assertions.assertNotNull(DbProvider.connection(), "Connection test Failed!");
    }

    @Test
    void testMoviecatDatabase() {
        Assertions.assertEquals(DbProvider.connection().getDatabase().getName(), "moviecat", "Database retrieval test Failed!");
    }
}
