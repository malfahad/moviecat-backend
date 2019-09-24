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

package com.moviecat.servicesImpl.genre;

import com.moviecat.ds.DbProvider;
import com.moviecat.model.Genre;
import com.moviecat.services.GenreServices;
import com.moviecat.servicesImpl.implementations.BaseService;

import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreServices {

    private static final Logger log = LogManager.getLogger(GenreServiceImpl.class);


    @Override
    public Genre createGenre(Genre genre) {
        BaseService.save(genre);
        log.info(genre.getTitle());
        Genre theSavedGenre = BaseService.fetchOne(Genre.class, genre.getId());
        log.info(theSavedGenre);
        if(!theSavedGenre.getTitle().isEmpty()){
            return theSavedGenre;
        }else{
            return null;
        }

    }

    @Override
    public Genre fetchGenre(String genreId) {
        Genre theSavedGenre = BaseService.fetchOne(Genre.class, genreId);
        if(!theSavedGenre.getTitle().isEmpty()){
            return theSavedGenre;
        }else{
            return null;
        }
    }

    @Override
    public List<Genre> fetchGenres() {
        List<Genre> theGenres = BaseService.fetch(Genre.class);
        if(theGenres.size() < 1){
            return new ArrayList<>();
        }else{
            return theGenres;
        }
    }

    @Override
    public Genre updateGenre(Genre genre) {
        Query<Genre> query = DbProvider.connection().createQuery(Genre.class).field("id").equal(genre.getId());
        UpdateOperations<Genre> updates = DbProvider.connection().createUpdateOperations(Genre.class).set("title",
                genre.getTitle()).set("description", genre.getDescription());
        BaseService.update(query, updates);
        Genre theSavedGenre = BaseService.fetchOne(Genre.class, genre.getId());
        if(!theSavedGenre.getTitle().isEmpty() && theSavedGenre.getTitle() == genre.getTitle()){
            return theSavedGenre;
        }else{
            return null;
        }

    }

    @Override
    public void deleteGenre(String genreId) {
        Query<Genre> query = DbProvider.connection().createQuery(Genre.class).field("id").contains(genreId);
        BaseService.delete(Genre.class, query);
    }

    @Override
    public Long countGenre() {
        return BaseService.count(Genre.class);
    }
}
