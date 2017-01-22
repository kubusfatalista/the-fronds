package com.fronds.model;

public enum UserRole {
    REGULAR, ADMIN;

    public String authority() {
        return "ROLE_"+this;
    }

}
