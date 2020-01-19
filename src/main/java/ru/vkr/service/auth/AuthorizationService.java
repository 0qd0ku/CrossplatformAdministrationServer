package ru.vkr.service.auth;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.dao.AuthorizationDao;
import ru.vkr.dao.ClientDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.model.AdminAuthorizationData;
import ru.vkr.service.ClientService;
import ru.vkr.util.TokenGenerator;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    protected AuthorizationDao authorizationDao;


    @Autowired
    public AuthorizationService(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public SessionData adminAuth(AdminAuthorizationData authData) {
        logger.debug("Start authorization by admin console: {}", authData);
        List<Long> result = authorizationDao.authorization(authData);
        if (CollectionUtils.isEmpty(result)) {
            throw new IllegalArgumentException("Login and pass is incorrect");
        }
        SessionData sessionData = authorizationDao.loadSessionDataByLoginId(result.get(0));
        if(sessionData != null)
        {
            return sessionData;
        }
        sessionData.setToken(TokenGenerator.generateToken());
        sessionData.setSessionType(SessionData.SessionType.ADMIN);
        authorizationDao.addSessionData(sessionData);
        return sessionData;
    }
    /*
     * Метод
     *
     */
    public SessionData clientCheckIn(ClientData authData) {
        ClientService cs = new ClientService(new ClientDao());
        logger.debug("Start authorization by client: {}", authData);
        ClientData client = cs.getClient(authData.getHostname());
        SessionData sessionData = new SessionData();
        sessionData.setToken(TokenGenerator.generateToken());
        if (Objects.isNull(client)) {
            client = cs.addClient(sessionData.getToken(), authData.getHostname(), authData.getOs(), authData.getOsType(), authData.getMacAddr());
        } else {
            client.setToken(sessionData.getToken());
            cs.updateClient(client);
        }
        sessionData.setSessionType(SessionData.SessionType.CLIENT);
        return sessionData;
    }

    public void updateSessionData(String token) {
        authorizationDao.updateSessionData(token);
    }

    public List<SessionData> loadSessionDataByToken(String token) {
        return authorizationDao.loadSessionDataByToken(token);
    }
}
