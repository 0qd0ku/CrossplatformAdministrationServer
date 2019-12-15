package ru.vkr.model.enums;

public enum TaskStatus {
    PREPARING("Preparing"),
    IN_QUEUE("In queue"),
    DOWNLOADING("Downloading"),
    INSTALLING("Installing");

    private String status;
    private TaskStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
