package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.ClientTaskStatusDto;
import ru.vkr.model.SimpleClientTaskDataDto;
import ru.vkr.model.TaskData;
import ru.vkr.model.enums.TaskStatus;

import java.util.List;

/*
 * Сервис для работы с задачами
 * TODO обработать исключения
 * */
@Service
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

    public void updateStatus(ClientTaskStatusDto clientTaskDataDto) {
        taskDao.updateStatus(clientTaskDataDto);
    }

    public void deleteById(Long id) {
        taskDao.deleteById(id);
    }

    public void updateTask(TaskData task) {
        taskDao.updateTask(task);
    }


    public List<TaskData> getTasksForClient(Long id) {
        return taskDao.getTasksForClient(id);
    }

    public TaskData addTaskForClient(Long idClient, Long idTask) {
        taskDao.addTaskForClient(idClient, idTask);
        return taskDao.getTask(idTask);
    }
    public void deleteTaskForClient(SimpleClientTaskDataDto simpleClientTaskDataDto) {
        taskDao.deleteTaskForClient(simpleClientTaskDataDto);
    }

    public List<ClientData> getClientsForTask(Long id) {
        return taskDao.getClientsForTask(id);
    }
}

