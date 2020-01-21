package ru.vkr.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vkr.exception.LoginDataNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @ExceptionHandler(LoginDataNotFoundException.class)
    public void illegalAction(HttpServletResponse response, LoginDataNotFoundException ex) throws IOException {
        response.setStatus(ex.getStatusCode().value());
        OBJECT_MAPPER.writeValue(response.getOutputStream(), ex.getMessage());
    }
}
