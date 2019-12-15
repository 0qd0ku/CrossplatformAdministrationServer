package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskData;

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

    public void addTask(TaskData task) {
        taskDao.addTask(task);
    }

    public TaskData getTask(Long id) {
        return taskDao.getTask(id);
    }

    public List<TaskData> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void deleteById(Long id) {
        taskDao.deleteById(id);
    }

    public void updateTask(TaskData task) {
        taskDao.updateTask(task);
    }
}
