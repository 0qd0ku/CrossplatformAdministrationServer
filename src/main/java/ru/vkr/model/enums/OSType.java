package ru.vkr.model.enums;

public enum OSType {
    X86("x86"),
    X64("x64");

    private final String osType;
    OSType(String osType) {
        this.osType = osType;
    }

    public String getValue() {
        return osType;
    }

    @Override
    public String toString() {
        return "OSType{" +
                "osType='" + osType + '\'' +
                '}';
    }
}
