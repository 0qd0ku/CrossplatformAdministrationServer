package ru.vkr.controler;

import com.logging.Logging;
import com.logging.LoggingLvl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vkr.model.*;
import ru.vkr.service.ClientService;
import ru.vkr.service.TaskService;
import ru.vkr.service.auth.AdminAuthorizationService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api/admin")
@Logging(level = LoggingLvl.INFO)
public class AdminApiController {

    private static final Logger logger = LoggerFactory.getLogger(AdminApiController.class);

    private final AdminAuthorizationService adminAuthorizationService;
    private final ClientService clientService;
    private final TaskService taskService;

    @Autowired
    public AdminApiController(AdminAuthorizationService adminAuthorizationService,
                              ClientService clientService,
                              TaskService taskService) {
        this.adminAuthorizationService = adminAuthorizationService;
        this.clientService = clientService;
        this.taskService = taskService;
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

    @GetMapping("/main")
    public String adminMainPage()
    {
        return "main";
    }

    @GetMapping("/all-tasks")
    public ModelAndView adminTasksPage()
    {
        ModelAndView modelAndView = new ModelAndView("tasks");
        List<TaskData> taskDataList = taskService.getAllTasks();
        if (!Objects.isNull(taskDataList))
            modelAndView.addObject("tasklist", taskDataList);
        return modelAndView;
    }

    /* Метод должен формировать список подключенных клиентов */
    @GetMapping("/clients")
    @ResponseBody
    public ClientPackDto adminClients() {
        return new ClientPackDto(clientService.getAllClients());
    }

    /* Метод должен формировать список задач */
    @GetMapping("/tasks")
    @ResponseBody
    public TaskPackDto adminTasks() {
        return new TaskPackDto(taskService.getAllTasks());
    }

    /* Метод создания новой задачи */
    @PostMapping("/add-task")
    public void adminAddTask(@RequestBody TaskData taskData) {
        taskService.addTask(taskData);
    }

    /* Метод удаления задачи */
    @DeleteMapping("/delete-task")
    public void adminDeleteTask(@RequestParam("id") Long id) {
        taskService.deleteById(id);
    }

    /* Метод обновления задачи */
    @PostMapping("/update-task")
    public void adminEditTask(@RequestBody TaskData taskData) {
        taskService.updateTask(taskData);
    }
    /* Метод назначения задачи на клиента */
    @GetMapping("/assign")
    public void adminAssignTask(@RequestParam(name = "id", required = true) Long clientID,
                                @RequestParam(name = "taskId", required = true) Long taskID) {
        taskService.addTaskForClient(clientID, taskID);
    }

    /* Метод отмены назначенной на клиента задачи */
    @DeleteMapping("/cancel-task")
    public void adminAssignTaskCancel(@RequestBody SimpleClientTaskDataDto simpleClientTaskDataDto) {
        taskService.deleteTaskForClient(simpleClientTaskDataDto);
    }
}
