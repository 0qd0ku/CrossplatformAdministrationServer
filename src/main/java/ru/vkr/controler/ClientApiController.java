package ru.vkr.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.model.TaskPackDto;
import ru.vkr.service.TaskService;
import ru.vkr.service.auth.ClientAuthorizationService;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {
    private final ClientAuthorizationService clientAuthorizationService;

    private final TaskService taskService;

    @Autowired
    public ClientApiController(ClientAuthorizationService clientAuthorizationService, TaskService taskService) {
        this.clientAuthorizationService = clientAuthorizationService;
        this.taskService = taskService;
    }

    @PostMapping(path = "/checkin")
    @ResponseBody
    public SessionData clientCheckin(@RequestBody ClientData clientData) {
        return clientAuthorizationService.process(clientData);
    }

    @GetMapping("/tasks")
    @ResponseBody
    public TaskPackDto getClientTaskList(@RequestParam(name = "clientID", required = true) Long id) {
        return new TaskPackDto(taskService.getTasksForClient(id));
    }

    @PostMapping("/task/status")
    public String toggleTaskStatus() {
        return null;
    }
}
