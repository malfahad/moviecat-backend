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

package com.moviecat.services;

import com.moviecat.model.Genre;

import java.util.List;

public interface GenreServices {

    Genre createGenre(Genre genre);

    Genre fetchGenre(String genreId);

    List<Genre> fetchGenres();

    Genre updateGenre(Genre genre);

    void deleteGenre(String genreId);

    Long countGenre();
}
