package ru.vkr.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.service.auth.ClientAuthorizationAuthService;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {
    private final ClientAuthorizationAuthService clientAuthorizationAuthService;
    @Autowired
    public ClientApiController(ClientAuthorizationAuthService clientAuthorizationAuthService) {
        this.clientAuthorizationAuthService = clientAuthorizationAuthService;
    }

    @PostMapping(path = "/checkin")
    @ResponseBody
    public SessionData clientCheckin(@RequestBody ClientData clientData) {
        SessionData sessionDataDto = clientAuthorizationAuthService.process(clientData);
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
