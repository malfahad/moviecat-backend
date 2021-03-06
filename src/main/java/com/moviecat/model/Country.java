package com.moviecat.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.Objects;

/**
 * Class to represent a country for Moviecat
 */

@Entity
public class Country extends DefaultModel {

    @Id
    @JsonProperty
    String id;
    @JsonProperty
    String name;
    @JsonProperty
    String code;

    @JsonCreator
    public Country(@JsonProperty String id, @JsonProperty String name, @JsonProperty String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    @JsonCreator
    public Country() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id.equals(country.id) &&
                Objects.equals(name, country.name) &&
                Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
