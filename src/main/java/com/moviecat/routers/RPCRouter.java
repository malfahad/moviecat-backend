
/*
 *  Licensed to the MovieCat, Inc. under one
 *  or more contributor license agreements. See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.
 *
 *  You may not use this file except in compliance
 *  with the License.
 *
 */

package com.moviecat.routers;

import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcMethod;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcOptional;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcParam;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcService;
import com.moviecat.ds.DbProvider;
import com.moviecat.model.Country;
import com.moviecat.model.Genre;
import com.moviecat.model.Role;
import com.moviecat.servicesImpl.genre.GenreServiceImpl;
import com.moviecat.servicesImpl.implementations.BaseService;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@JsonRpcService
public class RPCRouter {

    private static final Logger log = LogManager.getLogger(RPCRouter.class);
    GenreServiceImpl genreService = new GenreServiceImpl();

    /**
     * Health check
     * @return Boolean
     */
    @JsonRpcMethod("isAlive")
    public boolean isAlive() {
        return BaseService.isAlive();
    }

    /**
     * Fetches all countries
     * @return List<Country>
     */
    @JsonRpcMethod("fetchCountries")
    public List<Country> fetchCountries() {
        return BaseService.fetch(Country.class);
    }

    /**
     * Fetches countries in Paginated format
     * @param limit
     * @param page
     * @return List<Country>
     */
    @JsonRpcMethod("fetchCountriesLimit")
    public List<Country> fetchCountriesLimit(@JsonRpcParam("limit") final int limit, @JsonRpcParam("page") final int page) {
        return BaseService.fetch(Country.class, limit, page);
    }

    /**
     * Creates a new Role
     * @param id
     * @param roles
     * @param description
     */
    @JsonRpcMethod("createRole")
    public void createRole(@JsonRpcParam("id") final String id, @JsonRpcParam("role") final String roles, @JsonRpcParam("description") final String description) {
        Role role = new Role(id, roles, description);
        BaseService.save(role);
    }

    /**
     * Counts the number Roles that exist
     * @param item
     * @return Long
     */
    @JsonRpcMethod("count")
    public Long count(@JsonRpcParam("item") final String item) {
        Class clazz;
        try {
            clazz = Class.forName("com.moviecat.model." + item);
            return BaseService.count(clazz);
        } catch (ClassNotFoundException ex) {
            return -1L;
        }
    }

    /**
     * Fetches a Role given its ID
     * @param id
     * @return Role
     */
    @JsonRpcMethod("fetchRole")
    public Role fetchRole(@JsonRpcParam("id") final String id) {
        return BaseService.fetchOne(Role.class, id);
    }

    /**
     * Fetches all the Roles from data store
     * @return List<Role>
     */
    @JsonRpcMethod("fetchRoles")
    public List<Role> fetchRoles() {
        return BaseService.fetch(Role.class);
    }

    /**
     * Fetches Roles in Paginated Format
     * @param limit
     * @param page
     * @return List<Role>
     */
    @JsonRpcMethod("fetchRolesLimit")
    public List<Role> fetchRolesLimit(@JsonRpcParam("limit") final int limit, @JsonRpcParam("page") final int page) {
        return BaseService.fetch(Role.class, limit, page);
    }

    /**
     * Updates or edits a users Role
     * @param id
     * @param roles
     * @param description
     */
    @JsonRpcMethod("updateRole")
    public void updateRole(@JsonRpcParam("id") final String id, @JsonRpcParam("role") final String roles, @JsonRpcParam("description") final String description) {
        Query<Role> query = DbProvider.connection().createQuery(Role.class).field("id").equal(id);
        UpdateOperations<Role> updates = DbProvider.connection().createUpdateOperations(Role.class).set("role", roles).set("description", description);
        BaseService.update(query, updates);
    }

    /**
     * Deletes users Role given its ID
     * @param id
     */
    @JsonRpcMethod("deleteRole")
    public void deleteRole(@JsonRpcParam("id") final String id) {
        Query<Role> query = DbProvider.connection().createQuery(Role.class).field("id").equal(id);
        BaseService.delete(query);
    }

    /**
     * Creates a new Genre
     * @param id
     * @param title
     * @param description
     * @return Genre
     */
    @JsonRpcMethod("createGenre")
    public Genre createGenre(@JsonRpcOptional @JsonRpcParam("id") final String id,@JsonRpcOptional @JsonRpcParam("title") final String title, @JsonRpcOptional @JsonRpcParam("description") final String description){
        Genre genre = new Genre(id, title, description);
        return genreService.createGenre(genre);
    }

    /**
     * Fetches all the Genre
     * @return List<Genre>
     */
    @JsonRpcMethod("fetchGenres")
    public List<Genre> fetchGenres(){
        return genreService.fetchGenres();
    }

    /**
     * Fetches One Genre given its ID
     * @param id
     * @return Genre
     */
    @JsonRpcMethod("fetchGenre")
    public Genre fetchGenre(@JsonRpcParam("id") final String id){
        return genreService.fetchGenre(id);
    }

    /**
     * Updates an existing Genre or Creates the Genre if not exists
     * @param id
     * @param title
     * @param description
     * @return Genre
     */
    @JsonRpcMethod("updateGenre")
    public Genre updateGenre(@JsonRpcOptional @JsonRpcParam("id") final String id,@JsonRpcOptional @JsonRpcParam("title") final String title, @JsonRpcOptional @JsonRpcParam("description") final String description){
        Genre genre = new Genre(id, title, description);
        return genreService.updateGenre(genre);
    }

    /**
     * Deletes an existing Genre
     * @param id
     */
    @JsonRpcMethod("deleteGenre")
    public void deleteGenre(@JsonRpcParam("id") final String id){
        genreService.deleteGenre(id);
    }

    /**
     * Counts the number of Genre in the data store
     * @return Long
     */
    @JsonRpcMethod("countGenre")
    public Long countGenre(){
        return genreService.countGenre();
    }

}