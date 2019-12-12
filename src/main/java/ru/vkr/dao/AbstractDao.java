package ru.vkr.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDao {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate parameterJdbcTemplate;

}
