package ru.vkr.model;

import ru.vkr.model.enums.TaskStatus;

public class ClientTaskStatusInfo {
    private ClientData clientData;
    private TaskStatus taskStatus;

    public ClientData getClientData() {
        return clientData;
    }

    public ClientTaskStatusInfo setClientData(ClientData clientData) {
        this.clientData = clientData;
        return this;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public ClientTaskStatusInfo setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    @Override
    public String toString() {
        return "ClientTaskStatusInfo{" +
                "clientData=" + clientData +
                ", taskStatus=" + taskStatus +
                '}';
    }
}