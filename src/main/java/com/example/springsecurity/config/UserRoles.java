package com.example.springsecurity.config;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

import static com.example.springsecurity.config.UserPermissions.*;

public enum UserRoles {
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    STUDENT(Sets.newHashSet());

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }
}
