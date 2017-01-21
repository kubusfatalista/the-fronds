package com.fronds.database.model;

public enum UserRole {
    REGULAR, ADMIN;

    public String authority() {
        return "ROLE_"+this;
    }

}
