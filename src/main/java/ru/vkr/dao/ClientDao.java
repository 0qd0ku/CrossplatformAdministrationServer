package ru.vkr.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskData;

import java.util.List;

@Repository
public class ClientDao extends AbstractDao{

    private static final String GET_CLIENT_QUERY = "select from clients where hostname = :hostname";
    private static final String GET_ALL_CLIENTS_QUERY = "select * from clients";
    private static final String ADD_CLIENT_QUERY = "insert into clients(token, hostname, os, osType, macAddr) " +
            "values (:token, :hostName, :os, :osType, :macAddr)";

    private static final String DELETE_CLIENT_BY_ID = "delete clients where id = :id";
    private static final String UPDATE_CLIENT_BY_ID = "update clients set token = :token, hostname = :hostname, os = :os, osType = :osType, macAddr = :macAddr where id = :id";
    private static final String DELETE_CLIENT_BY_HOSTNAME = "delete clients where hostname = ?";



    private static final RowMapper<ClientData> clientDataListRowMapper = JdbcTemplateMapperFactory.newInstance().
            ignorePropertyNotFound().newRowMapper(ClientData.class);

    private static final RowMapper<TaskData> taskDataDtoRowMapper = JdbcTemplateMapperFactory.newInstance().
            ignorePropertyNotFound().newRowMapper(TaskData.class);


    public int addClient(ClientData client) {
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("token", client.getToken())
                .addValue("hostName", client.getHostname())
                .addValue("os", client.getOs())
                .addValue("osType", client.getOsType())
                .addValue("macAddr", client.getMacAddr());
        return parameterJdbcTemplate.update(ADD_CLIENT_QUERY, mapSource);
    }

    public ClientData getClient(String hostname) {
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("hostname", hostname);
        return parameterJdbcTemplate.query(GET_CLIENT_QUERY, mapSource, clientDataListRowMapper).get(0);
    }


    public List<ClientData> getAllClients() {
        return parameterJdbcTemplate.query(GET_ALL_CLIENTS_QUERY, clientDataListRowMapper);
    }

    public int deleteById(Long id) {
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("id", id);
        return parameterJdbcTemplate.update(DELETE_CLIENT_BY_ID, mapSource);
    }

    public int deleteByHostname(String hostname) {
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("hostname", hostname);
        return parameterJdbcTemplate.update(DELETE_CLIENT_BY_HOSTNAME, mapSource);
    }

    public int updateClient(ClientData client) {
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("id", client.getId())
                .addValue("token", client.getToken())
                .addValue("hostname", client.getHostname())
                .addValue("os", client.getOs())
                .addValue("osType", client.getOsType())
                .addValue("macAddr", client.getMacAddr());
        return parameterJdbcTemplate.update(UPDATE_CLIENT_BY_ID, mapSource);

    }
}
