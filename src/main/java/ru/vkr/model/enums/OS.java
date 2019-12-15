package ru.vkr.model.enums;

public enum OS {
    WINDOWS("Windows"),
    LINUX("Linux"),
    MACOS("MacOS");

    private String os;
    private OS(String os) {
        this.os = os;
    }

    public String getValue() {
        return os;
    }
}
