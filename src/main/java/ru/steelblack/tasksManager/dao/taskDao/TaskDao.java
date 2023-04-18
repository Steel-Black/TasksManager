package ru.steelblack.tasksManager.dao.taskDao;


import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDtoMapper;
import ru.steelblack.tasksManager.models.task.Task;

import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class TaskDao {

    private final JdbcTemplate jdbcTemplate;
    private final WorkerDao workerDao;
    private final TaskMapper taskMapper;
    private final TaskDtoMapper dtoMapper;

    public TaskDao(JdbcTemplate jdbcTemplate, WorkerDao workerDao, TaskMapper taskMapper, TaskDtoMapper dtoMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.workerDao = workerDao;
        this.taskMapper = taskMapper;
        this.dtoMapper = dtoMapper;
    }

    public List<TaskDto> getAllTasks() {
        return jdbcTemplate.query("select id, status, title from tasks", new TaskDtoMapper());
    }

    public void addTasksToDB(Task task) {
        new Thread(() -> saveTask(task)).start();
    }

    public Task getTaskById(int id) {
        Optional<Task> optionalTask = jdbcTemplate.query("select * from tasks where id=?", new Object[]{id}, taskMapper).stream().findAny();
        Task task = null;
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
        } else {
            log.error("Task not found");
        }
        return task;
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
        if (workerDao.getWorker(workerId) == null) {
            log.error("Worker with is ID does not exist");
        }
        jdbcTemplate.update("update tasks set performer=? where id=?", workerId, id);
    }

    private void saveTask(Task task) {
        jdbcTemplate.update("insert into tasks(title, description, time, status) values (?,?,?,?)",
                task.getTitle(),
                task.getDescription(),
                task.getTime(),
                task.getStatus().toString());
    }

    public List<TaskDto> getAllTasksByWorkerId(int id) {
      return jdbcTemplate.query("select * from tasks where performer=?", new Object[]{id}, dtoMapper);
    }
}
