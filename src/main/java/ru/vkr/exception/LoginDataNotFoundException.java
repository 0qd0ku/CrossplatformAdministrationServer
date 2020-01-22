package ru.vkr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class LoginDataNotFoundException extends HttpServerErrorException {
    public LoginDataNotFoundException(HttpStatus statusCode, String message) {
        super(statusCode, message);
    }
}
