package ru.steelblack.tasksManager.services.taskService;

import ru.steelblack.tasksManager.dto.TaskDto;
import ru.steelblack.tasksManager.models.Task;
import ru.steelblack.tasksManager.models.Worker;

import java.util.List;

public interface TaskService {
    void addTaskToQueue(Task task);

    List<TaskDto> getAllTasks();

    void addTasksToDB();

    Task getTaskById(int id);

    void updateTask(int id, Task task);

    void appointWorker(int id, int workerId);
}
