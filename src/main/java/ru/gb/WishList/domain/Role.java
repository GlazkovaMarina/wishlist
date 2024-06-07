package ru.gb.WishList.domain;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String label;

    Role(String label) {
        this.label = label;
    }
}
