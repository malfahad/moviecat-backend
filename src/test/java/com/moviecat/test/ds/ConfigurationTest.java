package com.moviecat.test.ds;

import com.moviecat.ds.Configuration;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

public class ConfigurationTest {

    @Test
    void testConfigurationLoading() {
        Assert.that(Configuration.config().size() > 0, "Loaded!");
    }

}
