package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.dao.ClientDao;
import ru.vkr.dao.TaskDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskData;
import ru.vkr.model.enums.OS;
import ru.vkr.model.enums.OSType;

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

    public ClientData addClient(String token, String hostname, OS os, OSType osType, String macAddr)
    {
        clientDao.addClient(new ClientData(hostname, os, osType, macAddr));
        return clientDao.getClient(hostname);
    }

    public ClientData getClient(String hostname) {
        return clientDao.getClient(hostname);
    }

    public ClientData getClient(Long id) {
        return clientDao.getClient(id);
    }

    public ClientData updateClient(ClientData client) {
        clientDao.updateClient(client);
        return clientDao.getClient(client.getHostname());
    }

    public List<ClientData> getAllClients()
    {
        return clientDao.getAllClients();
    }

    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }

    public void deleteByHostname(String hostname) {
        clientDao.deleteByHostname(hostname);
    }
}
