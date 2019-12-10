package ru.vkr.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/client")
public class ClientApiController {

    @PostMapping(path = "/checkin")
    @ResponseBody
    public String clientCheckin() {
        return null;
    }

    @GetMapping("/tasks")
    public String getClientTaskList() {
        return null;
    }

    @PostMapping("/task/status")
    public String toggleTaskStatus() {
        return null;
    }
}
