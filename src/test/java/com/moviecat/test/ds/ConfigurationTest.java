package com.moviecat.test.ds;

import com.moviecat.ds.Configuration;
import com.moviecat.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConfigurationTest extends BaseTest {

    @Test
    void testConfigurationLoading() {
        Assertions.assertTrue(Configuration.config().size() > 0);
    }
}
