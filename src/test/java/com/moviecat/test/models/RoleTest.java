package com.moviecat.test.models;

import com.moviecat.ds.DbProvider;
import com.moviecat.model.Role;
import com.moviecat.servicesImpl.implementations.BaseService;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoleTest {
    private static final Logger log = LogManager.getLogger(RoleTest.class);

    Role createRole(String id, String roles, String description) {
        Role role = new Role(id, roles, description);
        BaseService.save(role);
        return role;
    }

    void updateRole(String id, String role, String description) {
        Query<Role> query = DbProvider.connection().createQuery(Role.class).field("id").equal(id);
        UpdateOperations<Role> updates = DbProvider.connection().createUpdateOperations(Role.class).set("role", role).set("description", description);
        BaseService.update(query, updates);
    }

    void deleteRole(String id) {
        Role role = BaseService.fetchOne(Role.class, id);
        BaseService.delete(role);
    }

    Long theCount() {
        return BaseService.count(Role.class);
    }

    List<Role> fetch() {
        return BaseService.fetch(Role.class);
    }

    List<Role> fetchLimit(int limit, int page) {
        return BaseService.fetch(Role.class, limit, page);
    }

    Role fetchOne(String id) {
        return BaseService.fetchOne(Role.class, id);
    }

    @Test
    void tester() {
        Role roleA = createRole("131", "Lead Actor", "The Lead Actor");
        Role roleB = createRole("132", "Lead Actress", "The Lead Actress");
        Role roleC = createRole("133", "Supporting Actress", "The Supporting Actress");

        Assertions.assertTrue(fetch().size() == 3, "Records failed to be created in database!");

        updateRole(roleC.getId(), "BlackSmith", "The best blacksmith");

        Assertions.assertTrue(fetch().size() == 3, "Records failed to be created in database!");

        Assertions.assertTrue(fetchOne(roleB.getId()).getRole().equalsIgnoreCase("Lead Actress"), "FetchOne failed!");

        Assertions.assertEquals(java.util.Optional.of(3L), java.util.Optional.of(theCount()), "Failed to retrieve correct count value!");

        Assertions.assertEquals(2, fetchLimit(2, 1).size(), "FetchLimit call 1 failed!");

        Assertions.assertEquals(1, fetchLimit(2, 2).size(), "FetchLimit call 2 failed!");

        deleteRole(roleA.getId());
        deleteRole(roleB.getId());
        deleteRole(roleC.getId());

        Assertions.assertEquals(0, fetch().size(), "Delete failed");
    }
}
