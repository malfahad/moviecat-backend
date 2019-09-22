package com.moviecat.services.implementations;

import com.moviecat.ds.DbProvider;
import com.moviecat.model.DefaultModel;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * A simple base service class for generic service calls
 */
public class BaseService<T extends DefaultModel> {
    private static final Logger log = LogManager.getLogger(BaseService.class);

    public static boolean isAlive() {
        return true;
    }

    public static <T> List<T> fetch(Class obj) {
        return DbProvider.connection().createQuery(obj).find().toList();
    }

    public static <T> T fetchOne(Class obj, Object id) {
        return (T) DbProvider.connection().createQuery(obj).where("id=" + id).first();
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
}
