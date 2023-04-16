package ru.steelblack.tasksManager.services.taskService;

import org.springframework.stereotype.Service;
import ru.steelblack.tasksManager.dao.taskDao.TaskDao;
import ru.steelblack.tasksManager.dto.TaskDto;
import ru.steelblack.tasksManager.models.Task;
import ru.steelblack.tasksManager.models.Worker;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void addTaskToQueue(Task task) {
        taskDao.addTaskToQueue(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Override
    public void addTasksToDB() {
        taskDao.addTasksToDB();
    }

    @Override
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Override
    public void updateTask(int id, Task task) {
        taskDao.updateTask(id, task);
    }
    @Override
    public void appointWorker(int id, int workerId) {
        taskDao.appointWorker(id, workerId);
    }

}
