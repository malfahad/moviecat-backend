package com.moviecat.services;

import com.moviecat.ds.DbProvider;
import com.moviecat.model.Country;
import dev.morphia.Datastore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class InitScripts {

    private static final Logger log = LogManager.getLogger(InitScripts.class);
    static Datastore ds = DbProvider.connection();

    static {
        populateCountries();
    }

    /**
     * Call to po[ulate all supported countries
     */
    static void populateCountries() {
        if (ds.createQuery(Country.class).count() == 0) {

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
            ds.save(countries);

            log.info("Successfully populated countries!");
        } else {
            log.info("Countries already populated!");
        }
    }
}