package ru.vkr.model.enums;

public enum TaskType {
    BAT("Bat"),
    POWERSHELL("Powershell"),
    PROGRAM("Program"),
    SH("Sh");

    private String type;
    TaskType(String type) {
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
