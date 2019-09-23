package com.moviecat.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.Objects;

/**
 * Class to represent a #Role for Moviecat
 */
@Entity
public class Role extends DefaultModel {

    @Id
    @JsonProperty
    String id;
    @JsonProperty
    String role;
    @JsonProperty
    String description;

    @JsonCreator
    public Role(@JsonProperty String id, @JsonProperty String role, @JsonProperty String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    @JsonCreator
    public Role() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id.equals(role1.id) &&
                Objects.equals(role, role1.role) &&
                Objects.equals(description, role1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, description);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}