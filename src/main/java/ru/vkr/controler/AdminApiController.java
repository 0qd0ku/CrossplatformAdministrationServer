package ru.vkr.controler;

import com.logging.Logging;
import com.logging.LoggingLvl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vkr.model.AdminAuthorizationData;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.model.TaskDataDto;
import ru.vkr.service.auth.AdminAuthorizationAuthService;

import java.util.List;

@Controller
@RequestMapping("/api/admin")
@Logging(level = LoggingLvl.INFO)
public class AdminApiController {

    private static final Logger logger = LoggerFactory.getLogger(AdminApiController.class);

    private final AdminAuthorizationAuthService adminAuthorizationService;

    @Autowired
    public AdminApiController(AdminAuthorizationAuthService adminAuthorizationService) {
        this.adminAuthorizationService = adminAuthorizationService;
    }

    @PostMapping("/auth")
    @ResponseBody
    public SessionData adminAuthorization(@RequestBody AdminAuthorizationData authData) {
        logger.debug("Received request: {}", authData);
        SessionData sessionDataDto = adminAuthorizationService.process(authData);
        logger.debug("Handle response: {}", sessionDataDto);
        return sessionDataDto;
    }

    @GetMapping("/login")
    public String adminLoginPage()
    {
        return "login";
    }

    /* Метод должен формировать список подключенных клиентов */
    public List<ClientData> adminClients() { return null; }
    /* Метод должен формировать список задач */
    public List<TaskDataDto> adminTasks() { return null; }
    /* Метод создания новой задачи */
    public void adminAddTask() { return; }
    /* Метод удаления задачи */
    public void adminDeleteTask() { return;}
    /* Метод обновления задачи */
    public void adminEditTask() { return; }
    /* Метод назначения задачи на клиента */
    public void adminAssignTask() { return; }
    /* Метод отмены назначенной на клиента задачи */
    public void adminAssignTaskCancel() { return; }
}
