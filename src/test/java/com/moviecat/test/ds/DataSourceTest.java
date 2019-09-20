package com.moviecat.test.ds;

import com.moviecat.ds.DbProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataSourceTest {

    @Test
    void testMongoConnection() {
        Assertions.assertNotNull(DbProvider.connection());
    }

    @Test
    void testMoviecatDatabase() {
        Assertions.assertEquals(DbProvider.connection().getDatabase().getName(), "moviecat");
    }

}
