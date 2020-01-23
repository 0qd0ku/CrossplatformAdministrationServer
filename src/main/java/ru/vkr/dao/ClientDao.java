package ru.vkr.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.map.property.FieldMapperColumnDefinition;
import org.simpleflatmapper.reflect.Getter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vkr.model.ClientData;
import ru.vkr.model.enums.OS;
import ru.vkr.model.enums.OSType;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class ClientDao extends AbstractDao{

    private static final String GET_CLIENT_BY_ID = "SELECT * FROM clients WHERE id = :id";
    private static final String GET_CLIENT_QUERY = "SELECT * FROM clients WHERE hostname = :hostname";
    private static final String GET_ALL_CLIENTS_QUERY = "SELECT * FROM clients";
    private static final String ADD_CLIENT_QUERY = "INSERT INTO clients(hostname, os, osType, macAddr) " +
            "VALUES (:hostName, :os, :osType, :macAddr)";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM clients WHERE id = :id";
    private static final String UPDATE_CLIENT_BY_ID = "UPDATE clients SET token = :token, hostname = :hostname, os = :os, osType = :osType, macAddr = :macAddr " +
            "WHERE id = :id";
    private static final String DELETE_CLIENT_BY_HOSTNAME = "DELETE clients WHERE hostname = ?";

    private static final RowMapper<ClientData> clientDataListRowMapper = JdbcTemplateMapperFactory.newInstance()
            .addColumnDefinition("blocked",
                    FieldMapperColumnDefinition.customGetter((Getter<ResultSet, Boolean>) rs ->
                            rs.getInt("blocked") == 1))
            .addColumnDefinition("os",
                    FieldMapperColumnDefinition.customGetter((Getter<ResultSet, OS>) rs ->
                            OS.getOSByName(rs.getString("os"))))
            .addColumnDefinition("osType",
                    FieldMapperColumnDefinition.customGetter((Getter<ResultSet, OSType>) rs ->
                            OSType.getOsTypeByName(rs.getString("osType"))))
            .ignorePropertyNotFound().newRowMapper(ClientData.class);


    public int addClient(ClientData client) {
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("hostName", client.getHostname())
                .addValue("os", client.getOs().getValue())
                .addValue("osType", client.getOsType().getValue())
                .addValue("macAddr", client.getMacAddr())
                .addValue("isBlocked", client.getBlocked());
        return parameterJdbcTemplate.update(ADD_CLIENT_QUERY, mapSource);
    }

    public ClientData getClient(Long id) {
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("id", id);
        return parameterJdbcTemplate.query(GET_CLIENT_BY_ID, mapSource, clientDataListRowMapper).get(0);
    }

    public List<ClientData> getClient(String hostname) {
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("hostname", hostname);
        return parameterJdbcTemplate.query(GET_CLIENT_QUERY, mapSource, clientDataListRowMapper);
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
                .addValue("hostname", client.getHostname())
                .addValue("os", client.getOs().getValue())
                .addValue("osType", client.getOsType().getValue())
                .addValue("macAddr", client.getMacAddr());
        return parameterJdbcTemplate.update(UPDATE_CLIENT_BY_ID, mapSource);

    }
}
