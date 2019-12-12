package ru.vkr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vkr.model.AuthorizationData;
import ru.vkr.model.SessionData;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthorizationService extends AbstractService<SessionData, AuthorizationData>{

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    @Override
    public SessionData process(AuthorizationData authData) {
        logger.debug("Start authorization by client: {}", authData);
        return null;
    }

    private String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
