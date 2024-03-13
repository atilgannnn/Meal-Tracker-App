package com.mealtracker.domain.enums;

public enum RoleType {

    ROLE_USER("ROLE_USER"),

    ROLE_ADMIN("ROLE_ADMIN");

    private String name;

    private RoleType(String name) {

        this.name = name;

    }

    public String getName() {
        return name;
    }
}
