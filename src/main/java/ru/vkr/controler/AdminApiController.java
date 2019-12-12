package ru.vkr.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.AuthorizationDataDto;
import ru.vkr.model.SessionDataDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vkr.model.AuthorizationData;
import ru.vkr.model.SessionData;
import ru.vkr.service.AuthorizationService;

@Controller
@RequestMapping("/api/admin")
public class AdminApiController {

    private static final Logger logger = LoggerFactory.getLogger(AdminApiController.class);

    private final AuthorizationService authorizationService;

    @Autowired
    public AdminApiController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @CrossOrigin(origins = "https://localhost:8443")
    @PostMapping("/auth")
    public SessionData adminAuthorization(@RequestBody AuthorizationData authData) {
        logger.debug("Received request: {}", authData);
        SessionData sessionDataDto = authorizationService.process(authData);
        logger.debug("Handle response: {}", sessionDataDto);
        return sessionDataDto;
    }

    @GetMapping("/login")
    public String adminLoginPage()
    {
        return "login";
    }
}
