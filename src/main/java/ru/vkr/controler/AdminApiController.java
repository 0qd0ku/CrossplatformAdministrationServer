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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /* возвращает страницу авторизации*/
    @GetMapping("/login")
    public String adminLoginPage()
    {
        return "login";
    }

    /* возвращает главную страницу */
    @GetMapping("/main")
    public String adminMainPage()
    {
        return "main";
    }

    /* возвращает страницу со списком существующих задач */
    @GetMapping("/all-tasks")
    public ModelAndView adminTasksPage()
    {
        ModelAndView modelAndView = new ModelAndView("tasks/tasklist");
        List<TaskData> taskDataList = taskService.getAllTasks();
        if (!Objects.isNull(taskDataList))
            modelAndView.addObject("tasklist", taskDataList);
        return modelAndView;
    }

    @GetMapping("/task-clients")
    public ModelAndView adminTaskClients(@RequestParam("taskId") Long id) {
        ModelAndView modelAndView = new ModelAndView("tasks/taskclients");
        List<ClientData> clientDataList = taskService.getClientsForTask(id);
        if (!Objects.isNull(clientDataList))
            modelAndView.addObject("clientlist", clientDataList);
        modelAndView.addObject("taskId", id);
        return modelAndView;
    }

    @PostMapping("/delete-task")
    public String adminDeleteTask(@RequestParam("id") Long id)
    {
        taskService.deleteById(id);
        return "redirect:/api/admin/all-tasks";
    }

    @GetMapping("/edit-task-page")
    public ModelAndView adminEditTaskPage(@RequestParam("id") Long id)
    {
        return new ModelAndView("tasks/taskedit", "task", taskService.getTask(id));
    }

    @GetMapping("/create-task-page")
    public ModelAndView adminCreateTaskPage()
    {
        return new ModelAndView("tasks/taskcreate", "task", new TaskData());
    }

    @PostMapping("/create-task")
    @ResponseBody
    public void adminCreateTask(@RequestBody TaskData taskData)
    {
        taskService.addTask(taskData);
    }

    @PostMapping("/edit-task")
    @ResponseBody
    public void adminEditTask(@RequestBody TaskData taskData)
    {
        taskService.updateTask(taskData);
    }

    /* Метод должен формировать список клиентов */
    @GetMapping("/all-clients")
    @ResponseBody
    public ModelAndView adminClients() {
        ModelAndView modelAndView = new ModelAndView("clients/clientlist");
        List<ClientData> clientDataList = clientService.getAllClients();
        if (!Objects.isNull(clientDataList))
            modelAndView.addObject("clientlist", clientDataList);
        return modelAndView;
    }

    @PostMapping("/delete-client")
    public String adminDeleteClient(@RequestParam("id") Long id)
    {
        clientService.deleteById(id);
        return "redirect:/api/admin/all-clients";
    }

    @GetMapping("/client-tasks")
    public ModelAndView adminClientTasks(@RequestParam("clientId") Long id) {
        ModelAndView modelAndView = new ModelAndView("clients/clienttasks");
        List<TaskData> taskDataList = taskService.getTasksForClient(id);
        if (!Objects.isNull(taskDataList))
            modelAndView.addObject("tasklist", taskDataList);
        modelAndView.addObject("clientId", id);
        return modelAndView;
    }

    /* Метод назначения задачи на клиента */
    @PostMapping("/assign")
    public void adminAssignTask(@RequestBody SimpleClientTaskDataDto simpleClientTaskDataDto) {
        taskService.addTaskForClient(simpleClientTaskDataDto.getClientId(), simpleClientTaskDataDto.getTaskId());
    }

    /* Метод отмены назначенной на клиента задачи */
    @DeleteMapping("/cancel-task")
    @ResponseBody
    public void adminAssignTaskCancel(@RequestBody SimpleClientTaskDataDto simpleClientTaskDataDto) {
        taskService.deleteTaskForClient(simpleClientTaskDataDto);
    }

    @GetMapping("/add-task-to-client-page")
    public ModelAndView addTaskToClientPage(@RequestParam("clientId") Long id) {
        ModelAndView modelAndView = new ModelAndView("clients/clientaddtask");
        ClientData client = clientService.getClient(id);
        List<TaskData> taskDataList = taskService.getAllTasks().stream()
                .filter(a->a.getOs() == client.getOs() && a.getOsType() == client.getOsType())
                .collect(Collectors.toList());
        if (!Objects.isNull(taskDataList))
            modelAndView.addObject("tasklist", taskDataList);
        return modelAndView;
    }
}
