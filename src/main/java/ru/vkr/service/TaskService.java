package ru.vkr.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.ClientTaskStatusInfo;
import ru.vkr.model.TaskClientStatusInfo;
import ru.vkr.model.dto.ClientTaskStatusDto;
import ru.vkr.model.dto.SimpleClientTaskDataDto;
import ru.vkr.model.TaskData;

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

    @Transactional
    public void addTask(TaskData task) {
        taskDao.addTask(task);
    }

    public TaskData getTask(Long id) {
        return taskDao.getTask(id);
    }

    public List<TaskData> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Transactional
    public void updateStatus(ClientTaskStatusDto clientTaskDataDto) {
        taskDao.updateStatus(clientTaskDataDto);
    }

    public TaskData getTaskById(Long taskId) {
        List<TaskData> taskDataList = taskDao.getTaskById(taskId);
        if (CollectionUtils.isNotEmpty(taskDataList)) {
            return taskDataList.get(0);
        }
        return null;
    }

    public void deleteById(Long id) {
        taskDao.deleteById(id);
    }

    public void updateTask(TaskData task) {
        taskDao.updateTask(task);
    }


    public List<TaskClientStatusInfo> getTasksForClient(Long id) {
        return taskDao.getTasksForClient(id);
    }

    public List<Long> getActiveTasksByClientID(Long id) {
        return taskDao.getActiveTasksByClientID(id);
    }

    @Transactional
    public TaskData addTaskForClient(Long idClient, Long idTask) {
        taskDao.addTaskForClient(idClient, idTask);
        return taskDao.getTask(idTask);
    }

    @Transactional
    public void deleteTaskForClient(SimpleClientTaskDataDto simpleClientTaskDataDto) {
        taskDao.deleteTaskForClient(simpleClientTaskDataDto);
    }

    public List<ClientTaskStatusInfo> getClientsForTask(Long id) {
        return taskDao.getClientsForTask(id);
    }
}

