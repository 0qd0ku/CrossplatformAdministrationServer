package ru.vkr.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.vkr.model.TasktStatusInfo;
import ru.vkr.model.TaskData;
import ru.vkr.model.enums.OS;
import ru.vkr.model.enums.OSType;
import ru.vkr.model.enums.TaskStatus;
import ru.vkr.model.enums.TaskProcessType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskClientStatusInfoRowMapper implements RowMapper<TasktStatusInfo> {
    @Override
    public TasktStatusInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        return new TasktStatusInfo()
                .setStatus(TaskStatus.getTaskStatusByName(resultSet.getString("status")))
                .setTaskData(mapTaskData(resultSet));
    }

    private TaskData mapTaskData(ResultSet resultSet) throws SQLException {
        TaskData taskData =  new TaskData();
        taskData.setId(resultSet.getLong("id"));
        taskData.setName(resultSet.getString("name"));
        taskData.setTaskProcessType(TaskProcessType.valueOf(resultSet.getString("taskType")));
        taskData.setVersion(resultSet.getString("version"));
        taskData.setOs(OS.valueOf(resultSet.getString("os")));
        taskData.setOsType(OSType.valueOf(resultSet.getString("osType")));
        taskData.setPathToRunFile(resultSet.getString("pathToRunFile"));
        taskData.setTorrentFile(resultSet.getString("torrentFile"));
        return taskData;
    }
}
