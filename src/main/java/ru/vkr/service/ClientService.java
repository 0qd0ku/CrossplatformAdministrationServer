package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.dao.ClientDao;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskDataDto;

import java.util.List;

/*
 * Сервис для работы с задачами
 * TODO обработать исключения
 * */

@Service
public class ClientService {
    private ClientDao clientDao;
    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientData addClient(String token, String hostname, String os, String osType, String macAddr)
    {
        clientDao.addClient(new ClientData(token, hostname, os, osType, macAddr));
        return clientDao.getClient(hostname);
    }

    public ClientData getClient(String hostname) {
        return clientDao.getClient(hostname);
    }

    public ClientData updateClient(ClientData client) {
        clientDao.updateClient(client);
        return clientDao.getClient(client.getHostname());
    }

    public List<ClientData> getAllClients()
    {
        return clientDao.getAllClients();
    }

    public List<TaskDataDto> getTasksForClient(Long id) {
        return clientDao.getTasksForClient(id);
    }

    public TaskDataDto addTaskForClient(Long idClient, Long idTask) {
        clientDao.addTaskForClient(idClient, idTask);
        return new TaskDao().getTask(idTask);
    }

    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }

    public void deleteByHostname(String hostname) {
        clientDao.deleteByHostname(hostname);
    }
}
