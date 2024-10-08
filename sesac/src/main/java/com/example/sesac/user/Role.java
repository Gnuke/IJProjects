package com.example.sesac.user;

public enum Role {
    User("User"), Admin("Admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
