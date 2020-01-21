package ru.vkr.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.*;
import ru.vkr.model.dto.ClientTaskStatusDto;
import ru.vkr.model.dto.TaskPackDto;
import ru.vkr.service.TaskService;
import ru.vkr.service.AuthorizationService;

@Controller
@RequestMapping("/api/client")
public class ClientApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ClientApiController.class);

    private final AuthorizationService authorizationService;
    private final TaskService taskService;

    @Autowired
    public ClientApiController(AuthorizationService authorizationService, TaskService taskService) {
        this.authorizationService = authorizationService;
        this.taskService = taskService;
    }

    @PostMapping(path = "/checkin")
    @ResponseBody
    public SessionData clientCheckin(@RequestBody ClientData clientData) {
        logger.debug("Received request for checkin: {}", clientData);
        SessionData sessionData = authorizationService.clientCheckIn(clientData);
        logger.debug("Get response: {}", sessionData);
        return sessionData;
    }
    @GetMapping("/tasks")
    @ResponseBody
    public TaskPackDto getClientTaskList(@RequestParam(name = "clientId", required = true) Long id) {
        logger.debug("Received request for get clients tasks");
        TaskPackDto taskPackDto = new TaskPackDto(taskService.getActiveTasksByClientID(id));
        logger.debug("Get response data: {}", taskPackDto);
        return taskPackDto;
    }

    @GetMapping("get-task")
    @ResponseBody
    public TaskData getTaskById(@RequestParam(name = "taskId", required = true) Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/task/status")
    public void toggleTaskStatus(@RequestBody ClientTaskStatusDto clientTaskStatusDto) {
        logger.debug("Received request for toggle status: {}", clientTaskStatusDto);
        taskService.updateStatus(clientTaskStatusDto);
    }
}
