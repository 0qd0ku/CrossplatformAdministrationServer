package ru.vkr.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vkr.dao.ClientDao;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.service.ClientService;
import ru.vkr.util.TokenGenerator;

import java.util.Objects;

@Service
public class ClientAuthorizationService extends AbstractAuthService<SessionData, ClientData> {

    private static final Logger logger = LoggerFactory.getLogger(ClientAuthorizationService.class);

    public ClientAuthorizationService() {
        super(null);
    }


    /*
     * Метод
     *
     */
    @Override
    public SessionData process(ClientData authData) {
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
}
