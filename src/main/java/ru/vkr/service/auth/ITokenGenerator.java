package ru.vkr.service.auth;

import java.security.SecureRandom;
import java.util.Base64;

public interface ITokenGenerator {
    SecureRandom secureRandom = new SecureRandom();
    Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    default String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
