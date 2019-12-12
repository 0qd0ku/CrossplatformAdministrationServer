package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.dao.AbstractDao;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;

@Service
public class ClientsTaskService  {

    private TaskDao taskDao;

    @Autowired
    public ClientsTaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public Object process(ClientData clientData) {
        return null;
    }
}
