package ru.vkr.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.service.auth.ClientAuthorizationService;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {
    private final ClientAuthorizationService clientAuthorizationService;
    @Autowired
    public ClientApiController(ClientAuthorizationService clientAuthorizationService) {
        this.clientAuthorizationService = clientAuthorizationService;
    }

    @PostMapping(path = "/checkin")
    @ResponseBody
    public SessionData clientCheckin(@RequestBody ClientData clientData) {
        SessionData sessionDataDto = clientAuthorizationService.process(clientData);
        return sessionDataDto;
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
