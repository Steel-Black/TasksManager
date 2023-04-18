package ru.steelblack.tasksManager.dto.TaskDto;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.models.task.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class TaskDtoMapper implements RowMapper<TaskDto> {

    @Override
    public TaskDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TaskDto task = new TaskDto();

        task.setId(resultSet.getInt("id"));
        task.setTitle(resultSet.getString("title"));
        task.setStatus(Status.valueOf(resultSet.getString("status")));
        return task;
    }
}
