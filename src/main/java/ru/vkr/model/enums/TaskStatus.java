package ru.vkr.model.enums;

public enum TaskStatus {
    PREPARING("Preparing"),
    IN_QUEUE("In queue"),
    DOWNLOADING("Downloading"),
    INSTALLING("Installing");

    private final String name;
    TaskStatus(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }

    public static TaskStatus getTaskStatusByName(String name) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.name.equals(name)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Cant find status by name: " + name);
    }
    @Override
    public String toString() {
        return "TaskStatus{" +
                "name='" + name + '\'' +
                '}';
    }
}
