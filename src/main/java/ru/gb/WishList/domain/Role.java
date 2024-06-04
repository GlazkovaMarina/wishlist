package ru.gb.WishList.domain;

public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }
}
