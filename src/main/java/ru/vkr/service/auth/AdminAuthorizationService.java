package ru.vkr.service.auth;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vkr.dao.AuthorizationDao;
import ru.vkr.model.SessionData;
import ru.vkr.model.AdminAuthorizationData;
import ru.vkr.util.TokenGenerator;

import java.util.List;

@Service
public class AdminAuthorizationService extends AbstractAuthService<SessionData, AdminAuthorizationData> {

    private static final Logger logger = LoggerFactory.getLogger(AdminAuthorizationService.class);

    public AdminAuthorizationService(AuthorizationDao authorizationDao) {
        super(authorizationDao);
    }

    @Override
    public SessionData process(AdminAuthorizationData authData) {
        logger.debug("Start authorization by admin console: {}", authData);
        List<String> result = authorizationDao.authorization(authData);
        if (CollectionUtils.isEmpty(result)) {
            throw new IllegalArgumentException("Login and pass is incorrect");
        }
        SessionData sessionData = new SessionData();
        sessionData.setToken(TokenGenerator.generateToken());
        sessionData.setSessionType(SessionData.SessionType.ADMIN);
        return sessionData;
    }
}
