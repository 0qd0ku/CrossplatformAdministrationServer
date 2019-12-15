package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.dao.ClientTasksDao;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskData;

import java.util.List;

@Service
public class ClientTaskService  {

    private ClientTasksDao clientsTasksDao;

    @Autowired
    public ClientTaskService(ClientTasksDao clientsTasksDao) {
        this.clientsTasksDao = clientsTasksDao;
    }

    public List<TaskData> getTasksForClient(Long id) {
        return clientsTasksDao.getTasksForClient(id);
    }

    public TaskData addTaskForClient(Long idClient, Long idTask) {
        clientsTasksDao.addTaskForClient(idClient, idTask);
        return new TaskDao().getTask(idTask);
    }
    public void deleteTaskForClient(Long idClient, Long idTask) {
        clientsTasksDao.deleteTaskForClient(idClient, idTask);
    }

    private List<ClientData> getClientsForTask(Long id) {
        return clientsTasksDao.getClientsForTask(id);
    }
}
