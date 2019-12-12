package ru.vkr.dao;

import org.springframework.stereotype.Repository;
import ru.vkr.model.TaskDataDto;

import java.util.List;

@Repository
public class TaskDao extends AbstractDao {

    public int addTask(TaskDataDto task) {
        return jdbcTemplate.update("insert into tasks(id, name, taskType, version, os, osType, pathRunFile, torrentFile) values (?,?,?,?,?,?,?,?)",
                task.getId(), task.getName(), task.getTaskType(), task.getVersion(), task.getOs(), task.getOsType(), task.getPathRunFile(), task.getTorrentFile());
    }

    public TaskDataDto getTask(Long id) {
        return jdbcTemplate.query(
                "select from tasks where id=?",
                new Object[]{ id },
                (rs) -> {
                    return new TaskDataDto(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("taskType"),
                            rs.getString("version"),
                            rs.getString("os"),
                            rs.getString("osType"),
                            rs.getString("pathRunFile"),
                            rs.getString("torrentFile")
                    );
                }
        );
    }

    public List<TaskDataDto> getAllTasks() {
        return jdbcTemplate.query(
                "select * from tasks",
                (rs, rowNum) ->
                        new TaskDataDto(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("taskType"),
                                rs.getString("version"),
                                rs.getString("os"),
                                rs.getString("osType"),
                                rs.getString("pathRunFile"),
                                rs.getString("torrentFile")
                        )
        );
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete tasks where id = ?",
                id);
    }

    public int updateTask(TaskDataDto task) {
        return jdbcTemplate.update("update tasks set name = ?, taskType = ?, version = ?, os = ?, osType = ?, pathToRunFile = ?, torrentFile = ? where id = ?",
                task.getName(),
                task.getTaskType(),
                task.getVersion(),
                task.getOs(),
                task.getOsType(),
                task.getPathRunFile(),
                task.getTorrentFile(),
                task.getId());
    }
}
