package com.moviecat.test;

import com.moviecat.ds.DbProvider;
import org.junit.jupiter.api.AfterAll;

public class BaseTest {

    @AfterAll
    static void finish() {
        DbProvider.close();
    }
}
