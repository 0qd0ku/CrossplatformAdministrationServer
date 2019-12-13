package ru.vkr.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.ClientData;
import ru.vkr.service.ClientsTaskService;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {
    private final ClientsTaskService clientsTaskService;

    @Autowired
    public ClientApiController(ClientsTaskService clientsTaskService) {
        this.clientsTaskService = clientsTaskService;
    }

    @PostMapping(path = "/checkin")
    @ResponseBody
    public String clientCheckin(@RequestBody ClientData clientData) {
        return null;//clientsTaskService.process(null);
    }

    @GetMapping("/tasks")
    @ResponseBody
    public String getClientTaskList() {
        return null;
    }

    @PostMapping("/task/status")
    public String toggleTaskStatus() {
        return null;
    }
}
