package ru.vkr.service;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vkr.dao.AuthorizationDao;
import ru.vkr.model.AuthorizationData;
import ru.vkr.model.SessionData;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorizationService extends AbstractService<SessionData, AuthorizationData>{

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    private AuthorizationDao authorizationDao;

    public AuthorizationService(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    @Override
    public SessionData process(AuthorizationData authData) {
        logger.debug("Start authorization by client: {}", authData);
        List<String> result = authorizationDao.authorization(authData);
        if (CollectionUtils.isEmpty(result)) {
            throw new IllegalArgumentException("Login and pass is incorrect");
        }
        SessionData sessionData = new SessionData();
        sessionData.setToken(generateToken());
        return sessionData;
    }

    private String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
