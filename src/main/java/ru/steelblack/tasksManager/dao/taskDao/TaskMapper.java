package ru.steelblack.tasksManager.dao.taskDao;

import org.springframework.jdbc.core.RowMapper;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.models.Status;
import ru.steelblack.tasksManager.models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {

    private final WorkerDao workerDao;

    public TaskMapper(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Task task = new Task();

        task.setId(resultSet.getInt("id"));
        task.setTime(resultSet.getTime("time"));
        task.setTitle(resultSet.getString("title"));
        task.setDescription(resultSet.getString("description"));
        task.setStatus(Status.valueOf(resultSet.getString("status")));
        task.setPerformer(workerDao.getWorker(resultSet.getInt("performer")));

        return task;
    }
}
