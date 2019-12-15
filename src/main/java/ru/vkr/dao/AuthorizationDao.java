package ru.vkr.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vkr.model.AdminAuthorizationData;

import java.util.List;

@Repository
public class AuthorizationDao extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationDao.class);
    private static final String ADMIN_AUTH_QUERY = "SELECT login FROM logins WHERE login = :login AND password = :password";

    private static final RowMapper<String> ADMIN_LOGIN_COUNT = JdbcTemplateMapperFactory.newInstance()
            .newRowMapper(String.class);

    /**
     * Метод лезет в базу и ищет там пользователя с таким логин/паролем
     * @param adminAuthorizationData данные для авторизации
     * @return логин пользователя есть с такимии данными авторизации он  был найден в базе
     */
    public List<String> authorization(AdminAuthorizationData adminAuthorizationData) {
        logger.debug("Start find client: {}", adminAuthorizationData);
        mapSource = new MapSqlParameterSource()
                .addValue("login", adminAuthorizationData.getLogin())
                .addValue("password", adminAuthorizationData.getPassword());
        return parameterJdbcTemplate.query(ADMIN_AUTH_QUERY, mapSource, ADMIN_LOGIN_COUNT);
    }
}
