package ru.vkr.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientTaskStatusInfo;
import ru.vkr.model.TaskData;
import ru.vkr.model.TasktStatusInfo;
import ru.vkr.model.dto.ClientTaskStatusDto;
import ru.vkr.model.dto.SimpleClientTaskDataDto;
import ru.vkr.model.enums.TaskStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с задачами
 */
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


    public List<TasktStatusInfo> getTasksForClient(Long id) {
        return taskDao.getTasksForClient(id);
    }

    @Transactional
    public List<Long> getActiveTasksByClientID(Long clientId) {
        List<Long> taskIdList = taskDao.getActiveTasksByClientID(clientId);
        List<ClientTaskStatusDto> clientTaskStatusList = new ArrayList<>();
        taskIdList.forEach(taskId ->
                clientTaskStatusList.add(createClientTaskStatusDto(clientId, taskId)));
        clientTaskStatusList.forEach(this::updateStatus);
        return taskIdList;
    }

    private ClientTaskStatusDto createClientTaskStatusDto(Long clientId, Long taskId) {
        ClientTaskStatusDto clientTaskStatusDto = new ClientTaskStatusDto();
        clientTaskStatusDto.setTaskStatus(TaskStatus.IN_QUEUE);
        clientTaskStatusDto.setClientTaskData(new SimpleClientTaskDataDto(clientId, taskId));
        return clientTaskStatusDto;
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

