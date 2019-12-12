package ru.vkr.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskDataDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao extends AbstractDao{

    private static final RowMapper<ClientData> clientDataListRowMapper = JdbcTemplateMapperFactory.newInstance().
            ignorePropertyNotFound().newRowMapper(ClientData.class);

    private static final RowMapper<TaskDataDto> taskDataDtoRowMapper = JdbcTemplateMapperFactory.newInstance().
            ignorePropertyNotFound().newRowMapper(TaskDataDto.class);


    public ClientDao(JdbcTemplate jdbcTemplate) {
        parameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }
    public int addClient(ClientData client) {
        return jdbcTemplate.update(
                "insert into clients(id, token, hostname, os, osType, macAddr) values (?,?,?,?,?,?)",
                client.getId(),
                client.getToken(),
                client.getHostname(),
                client.getOs(),
                client.getOsType(),
                client.getMacAddr()
                );
    }

    public ClientData getClient(String hostname) {
        return jdbcTemplate.query(
                "select from clients where hostname=?",
                new Object[]{ hostname },
                (rs) -> {
                    return new ClientData(
                            rs.getLong("id"),
                            rs.getString("token"),
                            rs.getString("hostname"),
                            rs.getString("os"),
                            rs.getString("osType"),
                            rs.getString("macAddr")
                    );
                }
        );
    }


    public List<ClientData> getAllClients() {
        List<ClientData> clientDataList = new ArrayList<>();
        List<TaskDataDto> taks = new ArrayList<>();

        clientDataList.forEach(clientData ->  {

        });

        return parameterJdbcTemplate.query("select * from clients", clientDataListRowMapper);
    }

    private List<TaskDataDto> getTasksForClient(Long id) {
        String sql = "SELECT t.* , c.status FROM tasks t " +
                "JOIN clientstasks c " +
                "ON  c.taskId = t.id " +
                "WHERE c.clientId = :clId";
        parameterJdbcTemplate.query(sql, taskDataDtoRowMapper);
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete clients where id = ?",
                id);
    }

    public int deleteByHostname(String hostname) {
        return jdbcTemplate.update("delete clients where hostname = ?",
                hostname);
    }

    public int updateClient(ClientData client) {
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("clientID", client.getId());
        return parameterJdbcTemplate.update("update clients set token = ?, hostname = ?, clientID = :cleitnId, osType = ?, macAddr = ? where id = ?",mapSource);

    }
}
