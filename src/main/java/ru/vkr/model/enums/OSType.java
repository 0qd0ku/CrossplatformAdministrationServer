package ru.vkr.model.enums;

public enum OSType {
    X86("x86"),
    X64("x64");

    private String osType;
    private OSType(String osType) {
        this.osType = osType;
    }

    public String getValue() {
        return osType;
    }
}
