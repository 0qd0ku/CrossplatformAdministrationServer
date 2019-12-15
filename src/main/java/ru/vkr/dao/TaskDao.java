package ru.vkr.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.vkr.model.ClientData;
import ru.vkr.model.TaskData;

import java.util.List;

@Repository
public class TaskDao extends AbstractDao {
    private static final String ADD_TASK_QUERY = "insert into tasks(name, taskType, version, os, osType, pathRunFile, torrentFile)"+
            " values (:name, :taskType, :version, :os, :osType, :pathRunFile, :torrentFile)";
    private static final String GET_TASK_QUERY = "select * from tasks where id = :id";
    private static final String GET_ALL_TASKS = "select * from tasks";
    private static final String DELETE_TASK_BY_ID = "delete tasks where id = :id";
    private static final String UPDATE_TASK = "update tasks set name = :name, taskType = :taskType, " +
            "version = :version, os = :os, osType = :osType, pathToRunFile = :pathToRunFile, torrentFile = :torrentFile where id = :id";

    private static final RowMapper<TaskData> taskDataListRowMapper = JdbcTemplateMapperFactory.newInstance().
            ignorePropertyNotFound().newRowMapper(TaskData.class);
    private static final RowMapper<ClientData> clientDataListRowMapper = JdbcTemplateMapperFactory.newInstance().
            ignorePropertyNotFound().newRowMapper(ClientData.class);


    public int addTask(TaskData task) {
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("name", task.getName())
                .addValue("taskType", task.getTaskType())
                .addValue("version", task.getVersion())
                .addValue("os", task.getOs())
                .addValue("osType", task.getOsType())
                .addValue("pathRunFile", task.getPathRunFile())
                .addValue("torrentFile", task.getTorrentFile());

        return parameterJdbcTemplate.update(ADD_TASK_QUERY, mapSource);
    }

    public TaskData getTask(Long id) {
        MapSqlParameterSource mapSource =  new MapSqlParameterSource()
                .addValue("id", id);
        return parameterJdbcTemplate.query(GET_TASK_QUERY, mapSource, taskDataListRowMapper).get(0);
    }


    public List<TaskData> getAllTasks() {
        return parameterJdbcTemplate.query(GET_ALL_TASKS, taskDataListRowMapper);
    }

    public int deleteById(Long id) {
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("id", id);
        return parameterJdbcTemplate.update(DELETE_TASK_BY_ID, mapSource);
    }

    public int updateTask(TaskData task) {
        MapSqlParameterSource mapSource = new MapSqlParameterSource()
                .addValue("name", task.getName())
                .addValue("taskType", task.getTaskType())
                .addValue("version", task.getVersion())
                .addValue("os", task.getOs())
                .addValue("osType", task.getOsType())
                .addValue("pathToRunFile", task.getPathRunFile())
                .addValue("torrentFile", task.getTorrentFile());
        return parameterJdbcTemplate.update(UPDATE_TASK, mapSource);
    }
}
