package ru.vkr.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vkr.model.AuthorizationDataDto;
import ru.vkr.model.SessionDataDto;
import ru.vkr.service.AuthorizationService;

@Controller
@RequestMapping("/api/admin")
public class AdminApiController {

    Logger logger = LoggerFactory.getLogger(AdminApiController.class);

    private final AuthorizationService authorizationService;

    @Autowired
    public AdminApiController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/auth")
    public SessionDataDto adminAuthorization(@RequestBody AuthorizationDataDto authData) {
        logger.debug("Received request: {}", authData);
        SessionDataDto sessionDataDto = authorizationService.process(authData);
        logger.debug("Handle response: {}", sessionDataDto);
        return sessionDataDto;
    }


}
