package ru.vkr.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.ClientData;
import ru.vkr.model.ClientTaskStatusDto;
import ru.vkr.model.SessionData;
import ru.vkr.model.TaskPackDto;
import ru.vkr.service.TaskService;
import ru.vkr.service.auth.ClientAuthorizationService;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {
    private static final Logger logger = LoggerFactory.getLogger(ClientApiController.class);

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
        logger.debug("Received request for checkin: {}", clientData);
        SessionData sessionData = clientAuthorizationService.process(clientData);
        logger.debug("Get response: {}", sessionData);
        return sessionData;
    }

    @GetMapping("/tasks")
    @ResponseBody
    public TaskPackDto getClientTaskList(@RequestParam(name = "clientID", required = true) Long id) {
        logger.debug("Received request for get clients tasks");
        TaskPackDto taskPackDto = new TaskPackDto(taskService.getTasksForClient(id));
        logger.debug("Get response data: {}", taskPackDto);
        return taskPackDto;
    }

    @PostMapping("/task/status")
    public void toggleTaskStatus(@RequestBody ClientTaskStatusDto clientTaskStatusDto) {
        logger.debug("Received request for toggle status: {}", clientTaskStatusDto);
        taskService.updateStatus(clientTaskStatusDto);
    }
}
