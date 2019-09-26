package com.moviecat.servicesImpl.init;

import com.moviecat.model.Country;
import com.moviecat.servicesImpl.implementations.BaseService;
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
        if (BaseService.fetch(Country.class).size() == 0) {

            Integer counter = 0;
            Locale[] locales = Locale.getAvailableLocales();
            List<Country> countries = new LinkedList<>();
            Country country;

            for (Locale locale : locales) {
                if (Strings.isEmpty(locale.getCountry())) {
                    continue;
                }
                country = new Country((++counter).toString(), locale.getDisplayCountry(), locale.getCountry());

                log.info(counter + ": " + country);

                countries.add(country);
            }
            BaseService.save(countries);

            log.info("Successfully populated countries!");
        } else {
            log.info("Countries already populated!");
        }
    }
}