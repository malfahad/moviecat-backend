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

package com.moviecat.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.Objects;

@Entity
public class Genre {

    @Id
    @JsonProperty
    String id;

    @JsonProperty
    String title;

    @JsonProperty
    String description;

    @JsonCreator
    public Genre() {
    }

    /*
    @JsonCreator
    public Genre(@JsonProperty String title, @JsonProperty String description) {
        this.title = title;
        this.description = description;
    }

     */

    @JsonCreator
    public Genre(@JsonProperty String id, @JsonProperty String title, @JsonProperty String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(title, genre.title) &&
                Objects.equals(description, genre.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
