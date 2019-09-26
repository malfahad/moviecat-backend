package com.moviecat.servicesImpl.implementations;

import com.moviecat.ds.DbProvider;
import dev.morphia.query.FindOptions;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * A simple base service class for generic service calls
 */
public class BaseService {
    private static final Logger log = LogManager.getLogger(BaseService.class);

    public static boolean isAlive() {
        return true;
    }

    public static <T> List<T> fetch(Class obj) {
        return DbProvider.connection().createQuery(obj).find().toList();
    }

    public static <T> List<T> fetch(Class obj, Integer limit, Integer page) {

        if (page == null || page == 0) {
            page = 1;
        }

        if (limit == null || limit == 0) {
            limit = 10;
        }

        return DbProvider.connection().createQuery(obj).asList(new FindOptions().limit(limit).skip(limit * (page - 1)));
    }

    public static <T> T fetchOne(Class<T> obj, Object id) {
        return DbProvider.connection().createQuery(obj).field("id").equal(id).first();
    }

    public static <T> void save(T obj) {
        DbProvider.connection().save(obj);
    }

    public static <T> void save(List<T> obj) {
        DbProvider.connection().save(obj);
    }

    public static <T> void update(Query<T> query, UpdateOperations<T> updates) {
        DbProvider.connection().update(query, updates);
    }

    public static <T> void delete(T obj) {
        DbProvider.connection().delete(obj);
    }

    public static <T> void delete(Query<T> obj) {
        DbProvider.connection().delete(obj);
    }

    public static <T> Long count(Class<T> obj) {
        return DbProvider.connection().createQuery(obj).count();
    }
}
