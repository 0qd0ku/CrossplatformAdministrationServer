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

    public static OS getOSByName (String name) {
        for (OS os : OS.values()) {
            if (os.os.equals(name)) {
                return os;
            }
        }
        throw new IllegalArgumentException("Cant find OS, by type: " + name);
    }

    @Override
    public String toString() {
        return "OS{" +
                "os='" + os + '\'' +
                '}';
    }
}
