package com.moviecat.services.routers;

import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcMethod;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcOptional;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcParam;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcService;
import com.moviecat.ds.DbProvider;
import com.moviecat.model.Country;
import com.moviecat.model.Role;
import com.moviecat.services.implementations.BaseService;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@JsonRpcService
public class RPCRouter {

    private static final Logger log = LogManager.getLogger(RPCRouter.class);

    @JsonRpcMethod("isAlive")
    public boolean isAlive() {
        return BaseService.isAlive();
    }

    @JsonRpcMethod("fetchCountries")
    public List<Country> fetchCountries() {
        return BaseService.fetch(Country.class);
    }

    @JsonRpcMethod("fetchCountriesLimit")
    public List<Country> fetchCountriesLimit(@JsonRpcOptional @JsonRpcParam("limit") final int limit, @JsonRpcOptional @JsonRpcParam("page") final int page) {
        return BaseService.fetch(Country.class, limit, page);
    }

    @JsonRpcMethod("createRole")
    public void createRole(@JsonRpcOptional @JsonRpcParam("id") final String id, @JsonRpcOptional @JsonRpcParam("role") final String roles, @JsonRpcOptional @JsonRpcParam("description") final String description) {
        Role role = new Role(id, roles, description);
        BaseService.save(role);
    }

    @JsonRpcMethod("count")
    public Long count(@JsonRpcOptional @JsonRpcParam("item") final String item) {
        Class clazz;
        try {
            clazz = Class.forName("com.moviecat.model." + item);
            return BaseService.count(clazz);
        } catch (ClassNotFoundException ex) {
            return -1L;
        }
    }

    @JsonRpcMethod("fetchRole")
    public Role fetchRole(@JsonRpcOptional @JsonRpcParam("id") final String id) {
        return BaseService.fetchOne(Role.class, id);
    }

    @JsonRpcMethod("fetchRoles")
    public List<Role> fetchRoles() {
        return BaseService.fetch(Role.class);
    }

    @JsonRpcMethod("fetchRolesLimit")
    public List<Role> fetchRolesLimit(@JsonRpcOptional @JsonRpcParam("limit") final int limit, @JsonRpcOptional @JsonRpcParam("page") final int page) {
        return BaseService.fetch(Role.class, limit, page);
    }

    @JsonRpcMethod("updateRole")
    public void updateRole(@JsonRpcOptional @JsonRpcParam("id") final String id, @JsonRpcOptional @JsonRpcParam("role") final String roles, @JsonRpcOptional @JsonRpcParam("description") final String description) {
        Query<Role> query = DbProvider.connection().createQuery(Role.class).where("id=" + id);
        UpdateOperations<Role> updates = DbProvider.connection().createUpdateOperations(Role.class).set("role", roles).set("description", description);
        BaseService.update(query, updates);
    }

    @JsonRpcMethod("deleteRole")
    public void deleteRole(@JsonRpcOptional @JsonRpcParam("id") final String id) {
        Role role = BaseService.fetchOne(Role.class, id);
        BaseService.delete(role);
    }
}