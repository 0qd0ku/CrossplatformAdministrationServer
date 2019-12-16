package ru.vkr.model;

public class ClientTaskActionDto {
    private Long clientId;
    private Long taskId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "ClientTaskActionDto{" +
                "clientId=" + clientId +
                ", taskId=" + taskId +
                '}';
    }
}
