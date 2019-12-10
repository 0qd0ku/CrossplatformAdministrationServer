package ru.vkr.service;

import org.springframework.stereotype.Service;
import ru.vkr.model.AuthorizationDataDto;
import ru.vkr.model.SessionDataDto;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
public class AuthorizationService extends AbstractService<SessionDataDto, AuthorizationDataDto>{

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe


    @Override
    public SessionDataDto process(AuthorizationDataDto request) {
        return null;
    }

    private String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
