package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskDataDto;

import java.util.List;

/*
 * Сервис для работы с задачами
 * TODO обработать исключения
 * */

public class TaskService {
    private TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void addTask(TaskDataDto task) {
        taskDao.addTask(task);
    }

    public TaskDataDto getTask(Long id) {
        return taskDao.getTask(id);
    }

    private List<ClientData> getClientsForTask(Long id) {
        return taskDao.getClientsForTask(id);
    }

    public List<TaskDataDto> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void deleteById(Long id) {
        taskDao.deleteById(id);
    }

    public void updateTask(TaskDataDto task) {
        taskDao.updateTask(task);
    }
}
