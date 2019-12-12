package ru.vkr.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {
    private final ClientsTaskService clientsTaskService;

    @Autowired
    public ClientApiController(ClientsTaskService clientsTaskService) {
        this.clientsTaskService = clientsTaskService;
    }

    @CrossOrigin
    @PostMapping(path = "/checkin")
    public String clientCheckin(@RequestBody ClientData clientData) {
        return clientsTaskService.process();
    }

    @CrossOrigin
    @GetMapping("/tasks")
    public String getClientTaskList() {
        return null;
    }

    @CrossOrigin
    @PostMapping("/task/status")
    public String toggleTaskStatus() {
        return null;
    }
}
