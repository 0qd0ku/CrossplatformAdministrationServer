package ru.vkr.model.enums;

public enum TaskProcessType {
    BAT("Bat"),
    POWERSHELL("Powershell"),
    PROGRAM("Program"),
    SH("Sh");

    private final String type;
    TaskProcessType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }

    @Override
    public String toString() {
        return "TaskType{" +
                "type='" + type + '\'' +
                '}';
    }
}
