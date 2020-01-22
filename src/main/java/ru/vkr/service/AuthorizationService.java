package ru.vkr.service;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkr.dao.AuthorizationDao;
import ru.vkr.exception.LoginDataNotFoundException;
import ru.vkr.model.AdminAuthorizationData;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.util.TokenGenerator;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    private final AuthorizationDao authorizationDao;
    private final ClientService clientService;


    @Autowired
    public AuthorizationService(AuthorizationDao authorizationDao, ClientService clientService) {
        this.authorizationDao = authorizationDao;
        this.clientService = clientService;
    }

    @Transactional
    public SessionData adminAuth(AdminAuthorizationData authData) {
        logger.debug("Start authorization by admin console: {}", authData);
        List<Long> result = authorizationDao.authorization(authData);
        if (CollectionUtils.isEmpty(result)) {
            throw new LoginDataNotFoundException(HttpStatus.NOT_FOUND, "Admin not found in DB");
        }
        List<SessionData> sessionDataList = authorizationDao.loadSessionDataByLoginId(result.get(0));
        if (CollectionUtils.isEmpty(sessionDataList)) {
            SessionData sessionData = createSessionData(result.get(0), SessionData.SessionType.ADMIN);
            authorizationDao.addSessionData(sessionData);
            return sessionData;
        }
        SessionData sessionData = sessionDataList.get(0);
        updateSessionData(sessionData.getToken());
        return sessionData;
    }
    /*
     * Метод
     *
     */
    @Transactional
    public SessionData clientCheckIn(ClientData authData) {
        logger.debug("Start authorization by client: {}", authData);
        List<ClientData> clientDataList = clientService.getClient(authData.getHostname());
        if (CollectionUtils.isEmpty(clientDataList)) {
            clientService.addClient(authData);
            throw new LoginDataNotFoundException(HttpStatus.NOT_FOUND, "Client tasks can't start work," +
                    " because he is blocked");
        }
        ClientData client = clientDataList.get(0);
        List<SessionData> sessionDataList = authorizationDao.loadSessionDataByLoginId(client.getId());
        if (CollectionUtils.isNotEmpty(sessionDataList)) {
            SessionData sessionData = sessionDataList.get(0);
            updateSessionData(sessionData.getToken());
            return sessionData;
        }
        return createSessionData(client.getId(), SessionData.SessionType.CLIENT);
    }

    private SessionData createSessionData(long clientId, SessionData.SessionType sessionType) {
        SessionData sessionData = new SessionData();
        sessionData.setToken(TokenGenerator.generateToken());
        sessionData.setClientId(clientId);
        sessionData.setSessionType(sessionType);
        sessionData.setExpDate(new Date());
        return sessionData;
    }

    public void updateSessionData(String token) {
        authorizationDao.updateSessionData(token);
    }

    public List<SessionData> loadSessionDataByToken(String token) {
        return authorizationDao.loadSessionDataByToken(token);
    }
}
