package ru.vkr.service;

import org.springframework.stereotype.Service;
import ru.vkr.model.ClientData;

@Service
public class ClientsTaskService extends AbstractService<, ClientData> {
    @Override
    public Object process(ClientData clientData) {
        return ;
    }
}
