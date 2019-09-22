package com.moviecat.services.init;

import com.moviecat.ds.DbProvider;
import com.moviecat.model.Country;
import com.moviecat.services.implementations.BaseService;
import dev.morphia.Datastore;
import io.vertx.core.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Class to hold all initialization scripts that need to be run the first time the application server starts up
 */
public class InitScripts {

    private static final Logger log = LogManager.getLogger(InitScripts.class);
    static Datastore ds = DbProvider.connection();

    public static Future<Void> init() {
        Future<Void> future = Future.future(fut -> {
            try {
                populateCountries();
                fut.complete();
            } catch (Exception ex) {
                fut.fail(ex);
            }
        });
        return future;
    }

    /**
     * Call to populate all supported countries for the app
     */
    static void populateCountries() {
        if (BaseService.fetchOne(Country.class, 1) == null) {

            Integer counter = 0;
            Locale[] locales = Locale.getAvailableLocales();
            List<Country> countries = new LinkedList<>();
            Country country;

            for (Locale locale : locales) {
                if (Strings.isEmpty(locale.getCountry())) {
                    continue;
                }
                country = new Country((++counter).toString(), locale.getDisplayCountry(), locale.getCountry());
                countries.add(country);
            }
            BaseService.save(countries);

            log.info("Successfully populated countries!");
        } else {
            log.info("Countries already populated!");
        }
    }
}