package ru.vkr.service.auth;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vkr.dao.AuthorizationDao;
import ru.vkr.model.SessionData;
import ru.vkr.model.AdminAuthorizationData;

import java.util.List;

@Service
public class AdminAuthorizationAuthService extends AbstractAuthService<SessionData, AdminAuthorizationData> {

    private static final Logger logger = LoggerFactory.getLogger(AdminAuthorizationAuthService.class);

    public AdminAuthorizationAuthService(AuthorizationDao authorizationDao) {
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
        sessionData.setToken(generateToken());
        sessionData.setSessionType(SessionData.SessionType.ADMIN);
        return sessionData;
    }
}
