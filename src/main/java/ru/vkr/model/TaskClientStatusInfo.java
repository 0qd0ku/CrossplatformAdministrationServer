package ru.vkr.model;

import ru.vkr.model.enums.TaskStatus;

public class TaskClientStatusInfo {
    private TaskData taskData;
    private TaskStatus status;

    public TaskData getTaskData() {
        return taskData;
    }

    public TaskClientStatusInfo setTaskData(TaskData taskData) {
        this.taskData = taskData;
        return this;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskClientStatusInfo setStatus(TaskStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "TaskClientStatusInfo{" +
                "taskData=" + taskData +
                ", status=" + status +
                '}';
    }
}
