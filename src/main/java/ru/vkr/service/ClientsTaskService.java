package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.dao.TaskDao;

@Service
public class ClientsTaskService  {

    private TaskDao taskDao;

    @Autowired
    public ClientsTaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
