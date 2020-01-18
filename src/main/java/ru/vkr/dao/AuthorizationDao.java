package ru.vkr.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.map.property.FieldMapperColumnDefinition;
import org.simpleflatmapper.reflect.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vkr.model.AdminAuthorizationData;
import ru.vkr.model.SessionData;
import ru.vkr.model.dto.ClientTaskStatusDto;
import ru.vkr.model.enums.TaskStatus;
import ru.vkr.util.TokenGenerator;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class AuthorizationDao extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationDao.class);

    private static final int TOKEN_UPDATE_EXP_HOUR = 24;

    private static final String ADMIN_AUTH_QUERY = "SELECT login FROM logins WHERE login = :login AND password = :password";
    private static final String GET_SESSION_DATA_QUERY = "SELECT * FROM sessions WHERE token = :token";

    private static final String UPDATE_SESSION_DATA = "UPDATE sessions " +
            "(token, dttm_exp) " +
            "SET " +
            "(:token, :dttm_exp) " +
            "WHERE token = :token";

    private static final RowMapper<String> ADMIN_LOGIN_COUNT = JdbcTemplateMapperFactory.newInstance()
            .newRowMapper(String.class);
    private static final RowMapper<SessionData> SESSION_MAPPER = JdbcTemplateMapperFactory.newInstance()
            .addAlias("dttm_exp", "expDate")
            .addColumnDefinition("sessionType",
                    FieldMapperColumnDefinition.customGetter((Getter<ResultSet, SessionData.SessionType>) rs ->
                            SessionData.SessionType.getTypeByCode(rs.getInt("sessionType"))))
            .ignorePropertyNotFound().newRowMapper(SessionData.class);


    /**
     * Метод лезет в базу и ищет там пользователя с таким логин/паролем
     * @param adminAuthorizationData данные для авторизации
     * @return логин пользователя есть с такимии данными авторизации он  был найден в базе
     */
    public List<String> authorization(AdminAuthorizationData adminAuthorizationData) {
        logger.debug("Start find client: {}", adminAuthorizationData);
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("login", adminAuthorizationData.getLogin())
                .addValue("password", adminAuthorizationData.getPassword());
        return parameterJdbcTemplate.query(ADMIN_AUTH_QUERY, mapSource, ADMIN_LOGIN_COUNT);
    }

    public SessionData loadSessionDataByToken(String token) {
        logger.debug("Get session by token: {}", token);
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
        .addValue("token", token);
        return jdbcTemplate.query(GET_SESSION_DATA_QUERY, SESSION_MAPPER).get(0);
    }

    public void updateSessionData(String token) {
        logger.debug("Update session Data by token: {}", token);
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("token", TokenGenerator.generateToken())
                .addValue("dttm_exp", spotDate());
        jdbcTemplate.update(UPDATE_SESSION_DATA, mapSource);
    }

    private Date spotDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, TOKEN_UPDATE_EXP_HOUR);
        return cal.getTime();
    }
}
