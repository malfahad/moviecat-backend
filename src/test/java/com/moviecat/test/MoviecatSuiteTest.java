package com.moviecat.test;

import com.moviecat.ds.DbProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.moviecat.test.ds", "com.moviecat.test.services"})
public class MoviecatSuiteTest {

    @BeforeAll
    static void start() {
        DbProvider.open();
    }

    @AfterAll
    static void finish() {
        DbProvider.close();
    }
}
