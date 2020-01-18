package ru.vkr.model.enums;

public enum OS {
    WINDOWS("Windows"),
    LINUX("Linux"),
    MACOS("MacOS");

    private final String os;

    OS(String os) {
        this.os = os;
    }

    public String getValue() {
        return os;
    }

    @Override
    public String toString() {
        return "OS{" +
                "os='" + os + '\'' +
                '}';
    }
}
