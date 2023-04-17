package ru.steelblack.tasksManager.dao.taskDao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.dto.TaskDto;
import ru.steelblack.tasksManager.dto.TaskDtoMapper;
import ru.steelblack.tasksManager.models.Task;

import java.util.List;

@Component
public class TaskDao{

    private final JdbcTemplate jdbcTemplate;
    private final WorkerDao workerDao;

    public TaskDao(JdbcTemplate jdbcTemplate, WorkerDao workerDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.workerDao = workerDao;
    }

    public List<TaskDto> getAllTasks() {
        return jdbcTemplate.query("select id, status, title from tasks", new TaskDtoMapper());
    }

    public void addTasksToDB(Task task) {
        new Thread(() -> saveTask(task)).start();
      }

    public Task getTaskById(int id) {
       return jdbcTemplate.query("select * from tasks where id=?", new Object[]{id}, new TaskMapper(workerDao)).stream().findAny().orElse(null);
    }

    public void updateTask(int id, Task task) {
        jdbcTemplate.update("update tasks set title=?, description=?, time=?, status=? where id=?",
                task.getTitle(),
                task.getDescription(),
                task.getTime(),
                task.getStatus().toString(),
                id);
    }

    public void appointWorker(int id, int workerId) {
        if (workerDao.getWorker(workerId) != null){
        jdbcTemplate.update("update tasks set performer=? where id=?",workerId, id);}
    }

    private void saveTask(Task task) {
        jdbcTemplate.update("insert into tasks(title, description, time, status) values (?,?,?,?)",
                task.getTitle(),
                task.getDescription(),
                task.getTime(),
                task.getStatus().toString());
    }
}