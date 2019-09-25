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

package com.moviecat.test.routers;

import com.moviecat.model.Role;
import org.junit.Before;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JSONRPC")
class RPCRouterTest {

    static RPCRouter routedMethods;

    @BeforeAll
    static void setUp() {
        routedMethods = new RPCRouter();
    }

    @Nested
    @DisplayName("Countries Method Should")
    class CountriesTest{

        @Test
        @DisplayName("return true at all times")
        void isAlive() {
            assertTrue(routedMethods.isAlive());
        }

        @Test
        @DisplayName("fetch 114 countries")
        void fetchCountries() {
            assertEquals(114, routedMethods.fetchCountries().size());
        }

        @Test
        @DisplayName("fetch 10 countries per page")
        void fetchCountriesLimit() {
            assertEquals(10, routedMethods.fetchCountriesLimit(10,1).size());
        }
    }

    @Nested
    @DisplayName("Roles Method Should")
    class RolesTest{

        @BeforeEach
        void setUp() {
            routedMethods.createRole("34", "test","this is a test role");
        }

        @AfterEach
        void tearDown() {
            routedMethods.deleteRole("34");
        }

        @Test
        @DisplayName("create new roles")
        void createRole() {
            assertEquals("test", routedMethods.fetchRole("34").getRole());
        }

        @Test
        @DisplayName("count number of existing roles")
        void count() {
            assertNotNull(routedMethods.count("Role"));
        }

        @Test
        @DisplayName("fetch 1 role given its id")
        void fetchRole() {
            Role fetchedRole = routedMethods.fetchRole("34");
            assertEquals("34", fetchedRole.getId());
        }

        @Test
        @DisplayName("fetch all the roles")
        void fetchRoles() {
            assertTrue(routedMethods.fetchRoles().size() > 0);
        }

        @Test
        @DisplayName("fetch 10 roles on page 1")
        void fetchRolesLimit() {
            assertTrue(routedMethods.fetchRolesLimit(10, 1).size() < 11);
        }

        @Test
        @DisplayName("update a given role")
        void updateRole() {
            routedMethods.updateRole("34", "Test", "New Description");
            assertEquals("Test", routedMethods.fetchRole("34").getRole());
        }

        @Test
        @DisplayName("delete a given role")
        void deleteRole() {
            assertNotNull(routedMethods.fetchRole("34").getRole());
        }

    }

    @Nested
    @DisplayName("Genre Method Should")
    class GenreTest{

        @BeforeEach
        void setUp(){
            routedMethods.createGenre("23", "Mr. Ibu in London Part 1", "Ibu acting funny in London");
        }

        @AfterEach
        void tearDown(){
            routedMethods.deleteGenre("23");
        }


        @Test
        @DisplayName("create a new Genre")
        void createGenre() {
            routedMethods.createGenre("239", "Mr. Ibu in London", "Ibu acting funny in London");
            assertEquals("Mr. Ibu in London", routedMethods.fetchGenre("239").getTitle());
        }

        @Test
        @DisplayName("fetch all the Genres")
        void fetchGenres() {
            assertTrue(routedMethods.fetchGenres().size()>0);
        }

        @Test
        @DisplayName("fetch one Genre given its id")
        void fetchGenre() {
            assertEquals("Mr. Ibu in London Part 1", routedMethods.fetchGenre("23").getTitle());
        }

        @Test
        @DisplayName("update the Genre")
        void updateGenre() {
            routedMethods.updateGenre("23", "Osuophia in London", "This time it is Osuophia");
            assertEquals("Osuophia in London", routedMethods.fetchGenre("23").getTitle());
        }

        @Test
        @DisplayName("delete the Genre")
        void deleteGenre() {
            assertNotNull(routedMethods.fetchGenre("23").getTitle());
        }

        @Test
        @DisplayName("count all the Genres")
        void countGenre() {
            assertNotNull(routedMethods.countGenre());
        }
    }

}