package ru.vkr.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vkr.model.AuthorizationData;
import ru.vkr.model.SessionData;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorizationDao extends AbstractDao{

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationDao.class);
    private static final String AUTH_QUERY = "SELECT login FROM logins WHERE login = :login AND password = :password";

    private static final RowMapper<String> LOGIN_COUNT = JdbcTemplateMapperFactory.newInstance()
            .newRowMapper(String.class);

    /**
     * Метод оезет в базу и ищет там пользователя с таким логин/паролем
     * @param authorizationData данные для авторизации
     * @return логин пользователя есть с такимии данными авторизации он  был найден в базе
     */
    public List<String> authorization(AuthorizationData authorizationData) {
        logger.debug("Start find client: {}", authorizationData);
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("login", authorizationData.getLogin())
                .addValue("password", authorizationData.getPassword());
        return parameterJdbcTemplate.query(AUTH_QUERY, mapSource, LOGIN_COUNT);
    }
}
