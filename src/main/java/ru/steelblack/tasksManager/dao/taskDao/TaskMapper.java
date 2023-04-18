package ru.steelblack.tasksManager.dao.taskDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.steelblack.tasksManager.dao.workerDao.WorkerMapper;
import ru.steelblack.tasksManager.models.task.Status;
import ru.steelblack.tasksManager.models.task.Task;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskMapper implements RowMapper<Task> {

    private final JdbcTemplate jdbcTemplate;
    private final WorkerMapper mapper;
    public TaskMapper(JdbcTemplate jdbcTemplate, WorkerMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Task task = new Task();

        task.setId(resultSet.getInt("id"));
        task.setTime(resultSet.getDate("time"));
        task.setTitle(resultSet.getString("title"));
        task.setDescription(resultSet.getString("description"));
        task.setStatus(Status.valueOf(resultSet.getString("status")));
        task.setPerformer(getWorker(resultSet.getInt("performer")));

        return task;
    }

    private Worker getWorker(long id){
      return jdbcTemplate.query("select * from workers where id =?",
                new Object[]{id},
                mapper).stream().findAny().orElse(null);
    }
}
