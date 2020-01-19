package ru.vkr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import ru.vkr.model.SessionData;
import ru.vkr.service.auth.AuthorizationService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Configuration
public class ServletConfig implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletConfig.class);
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String CONTENT_TYPE = "application/json";


    @Value("#{'${urls.not.requiring.auth}'.split(',')}")
    private List<String> urlsNotRequiringAuth = new ArrayList<>(0);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private AuthorizationService authorizationService;

    @Autowired
    public ServletConfig(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (urlsNotRequiringAuth.stream().anyMatch(url -> request.getRequestURI().startsWith(url))) {
            // не использовать авторизацию для заданных URL
            LOGGER.debug("skipped auth for URL {}", request.getRequestURI());
            filterChain.doFilter(request, servletResponse);
            return;
        }
        String authToken = request.getHeader(AUTH_HEADER_NAME);
        if (StringUtils.isBlank(authToken)) {
            Optional<Cookie> coockie = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).findFirst();
            if (coockie.isPresent()) {
                authToken = coockie.get().getValue();
            }
            if (StringUtils.isBlank(authToken)) {
                sendResponse((HttpServletResponse) servletResponse, "Session token is null", HttpStatus.UNAUTHORIZED);
                return;
            }
        }

        List<SessionData> sessionDataList = authorizationService.loadSessionDataByToken(authToken);

        if (CollectionUtils.isNotEmpty(sessionDataList)) {
            SessionData sessionData = sessionDataList.get(0);
            switch (sessionData.getSessionType()) {
                case ADMIN:
                    authorizationService.updateSessionData(authToken);
                    break;
                case CLIENT:
                    if (sessionData.getExpDate().before(new Date())) {
                        LOGGER.debug("Session by token {} is Expired", authToken);
                        sendResponse((HttpServletResponse) servletResponse, "Session token is Expired", HttpStatus.UNAUTHORIZED);
                        return;
                    }
            }
        } else {
            // сессия не найдена, значит возвращаем ошибку авторизации
            LOGGER.debug("Session by token {} is not found", authToken);
            sendResponse((HttpServletResponse) servletResponse, "Session not found. Auth token is invalid", HttpStatus.UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, servletResponse);
    }

    private void sendResponse(HttpServletResponse response,
                              String message,
                              HttpStatus httpStatus) throws IOException {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(httpStatus.value());
        objectMapper.writeValue(response.getOutputStream(), message);
    }
}
