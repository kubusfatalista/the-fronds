package com.fronds.domain.model;

public enum UserRole {
    REGULAR, ADMIN;

    public String authority() {
        return "ROLE_"+this;
    }

}
