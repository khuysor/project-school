package com.huysor.projectschool.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum Role {
    OWNER(Set.of(Permission.WRITE,Permission.UPDATE,Permission.DELETE,Permission.READ)),
    ADMIN(Set.of(Permission.WRITE,Permission.UPDATE,Permission.DELETE,Permission.READ)),
    USER(Set.of(Permission.WRITE,Permission.UPDATE,Permission.DELETE,Permission.READ));
    private Set<Permission> permissions;
}
