package ru.vkr.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    NEW("New"),
    PREPARING("Preparing"),
    IN_QUEUE("In queue"),
    DOWNLOADING("Downloading"),
    ERROR_INSTALLING( "Error installing"),
    INSTALLING("Installing");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static TaskStatus getTaskStatusByName(String name) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.status.equals(name)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Cant find status by name: " + name);
    }
    @Override
    public String toString() {
        return "TaskStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
