package ru.vkr.dao.mapper;


import org.springframework.jdbc.core.RowMapper;
import ru.vkr.model.ClientData;
import ru.vkr.model.ClientTaskStatusInfo;
import ru.vkr.model.enums.OS;
import ru.vkr.model.enums.OSType;
import ru.vkr.model.enums.TaskStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientTaskStatusInfoRowMapper implements RowMapper<ClientTaskStatusInfo> {

    @Override
    public ClientTaskStatusInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ClientTaskStatusInfo()
                .setTaskStatus(TaskStatus.getTaskStatusByName(resultSet.getString("status")))
                .setClientData(mapClientData(resultSet));
    }

    private ClientData mapClientData(ResultSet resultSet) throws SQLException {
        return new ClientData()
                .setId(resultSet.getLong("id"))
                .setHostname(resultSet.getString("hostName"))
                .setOs(OS.valueOf(resultSet.getString("os")))
                .setOsType(OSType.valueOf(resultSet.getString("osType")))
                .setMacAddr(resultSet.getString("macAddr"));
    }
}
