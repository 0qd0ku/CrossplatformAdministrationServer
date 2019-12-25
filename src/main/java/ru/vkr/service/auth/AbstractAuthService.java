package ru.vkr.service.auth;

import ru.vkr.dao.AuthorizationDao;

public abstract class AbstractAuthService<T, R> {
    protected AuthorizationDao authorizationDao;

    public abstract T process(R request);

    public AbstractAuthService(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }
}
