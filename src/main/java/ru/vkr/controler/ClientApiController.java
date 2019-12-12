package ru.vkr.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {

    @CrossOrigin(origins = "http://localhost:8443")
    @PostMapping(path = "/checkin")
    public String clientCheckin() {
        return null;
    }

    @CrossOrigin(origins = "http://localhost:8443")
    @GetMapping("/tasks")
    public String getClientTaskList() {
        return null;
    }

    @CrossOrigin(origins = "http://localhost:8443")
    @PostMapping("/task/status")
    public String toggleTaskStatus() {
        return null;
    }
}
